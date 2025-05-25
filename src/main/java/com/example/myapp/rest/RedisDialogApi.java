package com.example.myapp.rest;

import com.example.myapp.dao.entity.Message;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Tag(name = "Управление диалогами. Необходим Token Bearer!", description = "Методы для работы с диалогами между пользователями")
public interface RedisDialogApi {
    @PostMapping("/{user_id}/send")
    @Operation(summary = "Отправка сообщения", description = "Отправляет сообщение пользователю")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно отправлено сообщение"),
            @ApiResponse(responseCode = "403", description = "Необходима авторизация"),
    })
    @SecurityRequirement(name = "bearerAuth")
    ResponseEntity<Void> sendMessage(
            @Parameter(hidden = true) Principal principal,
            @Parameter(description = "Идентификатор пользователя, которому отправляется сообщение") @PathVariable("user_id") UUID receiverId,
            @RequestBody String text
    );

    @GetMapping("/{user_id}/list")
    @Operation(summary = "Получение диалога", description = "Возвращает список сообщений между двумя пользователями")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Диалог успешно получен"),
            @ApiResponse(responseCode = "403", description = "Необходима авторизация"),
    })
    @SecurityRequirement(name = "bearerAuth")
    ResponseEntity<List<Message>> getDialog(
            @Parameter(hidden = true) Principal principal,
            @Parameter(description = "Идентификатор пользователя, с которым ведется диалог") @PathVariable("user_id") UUID friendId
    );
}
