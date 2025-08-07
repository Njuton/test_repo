package com.example.myapp.rest;

import com.example.myapp.dao.entity.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author rmartynov
 * @since 04.05.2025
 */
@RestController
@RequestMapping("/dialog")
public class DialogController implements DialogApi {

    private final RestTemplate restTemplate;
    private final String dialogServiceBaseUrl;

    public DialogController(RestTemplate restTemplate,
                            @Value("${dialog-service.base-url}") String dialogServiceBaseUrl) {
        this.restTemplate = restTemplate;
        this.dialogServiceBaseUrl = dialogServiceBaseUrl;
    }

    @PostMapping("/{user_id}/send")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> sendMessage(Principal principal, @PathVariable("user_id") UUID receiverId, @RequestBody String text) {
        UUID senderId = UUID.fromString(principal.getName());
        String url = dialogServiceBaseUrl + "/dialog/from/" + senderId + "/to/" + receiverId + "/send";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(text, headers);
        try {
            ResponseEntity<Message> msgResponse = restTemplate.postForEntity(url, request, Message.class);
            if (msgResponse.getStatusCode().is2xxSuccessful() && msgResponse.getBody() != null) {
                Message message = msgResponse.getBody();
                try {
                    // Увеличиваем счетчик непрочитанных сообщений
                    restTemplate.postForEntity(dialogServiceBaseUrl + "/counters/increment/" + receiverId, null, Void.class);
                    return ResponseEntity.ok().build();
                } catch (Exception e) {
                    // Компенсация: удаляем сообщение, если не удалось увеличить счетчик
                    String deleteUrl = dialogServiceBaseUrl + "/dialog/message/" + message.getId();
                    restTemplate.delete(deleteUrl);
                    return ResponseEntity.status(500).body(null);
                }
            }
            return ResponseEntity.status(msgResponse.getStatusCode()).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{user_id}/list")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Message>> getDialog(Principal principal, @PathVariable("user_id") UUID friendId) {
        UUID userId = UUID.fromString(principal.getName());
        String url = dialogServiceBaseUrl + "/dialog/" + userId + "/" + friendId + "/list";
        ResponseEntity<Message[]> response = restTemplate.getForEntity(url, Message[].class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            List<Message> dialog = Arrays.asList(response.getBody());
            List<UUID> unreadMessageIds = dialog.stream()
                    .filter(x -> !x.isRead())
                    .map(Message::getId)
                    .collect(Collectors.toList());
            // Делаем сообщения прочитанными
            restTemplate.postForEntity(dialogServiceBaseUrl + "/dialog/mark-as-read", unreadMessageIds, Void.class);
            // Попытка уменьшить счетчик непрочитанных сообщений
            try {
                restTemplate.postForEntity(dialogServiceBaseUrl + "/counters/decrement/" + userId + "/" + unreadMessageIds.size(), null, Void.class);
            } catch (Exception e) {
                // SAGA делаем компенсирующую операцию, делая эти же сообщения непрочитанными
                restTemplate.postForEntity(dialogServiceBaseUrl + "/dialog/mark-as-unread", unreadMessageIds, Void.class);
                return ResponseEntity.status(500).body(null);
            }
            return ResponseEntity.ok(dialog);
        } else {
            return ResponseEntity.status(response.getStatusCode()).build();
        }
    }
}
