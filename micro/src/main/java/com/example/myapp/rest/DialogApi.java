package com.example.myapp.rest;

import com.example.myapp.dao.entity.Message;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(
        name = "Управление диалогами.",
        description = "Методы для работы с диалогами между пользователями"
)
@RequestMapping("/dialog")
public interface DialogApi {

    @PostMapping("/{sender_id}/{receiver_id}/send")
    @Operation(description = "Отправляет сообщение другому пользователю"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно отправлено сообщение"),
    })
    ResponseEntity<Void> sendMessage(@Parameter(description = "ID отправителя") @PathVariable("sender_id") UUID senderId,
                                     @Parameter(description = "ID получателя") @PathVariable("receiver_id") UUID receiverId,
                                     @RequestBody String text
    );

    @GetMapping("/{user_id}/{friend_id}/list")
    @Operation(
            description = "Возвращает список сообщений между двумя пользователями"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Диалог успешно получен"),
    })
    ResponseEntity<List<Message>> getDialog(
            @Parameter(description = "ID пользователя (вы)") @PathVariable("user_id") UUID userId,
            @Parameter(description = "ID второго участника диалога") @PathVariable("friend_id") UUID friendId
    );
}