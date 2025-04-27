package com.example.myapp.rest;

import com.example.myapp.dao.entity.User;
import com.example.myapp.service.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Tag(name = "Управление друзьями. Необходим Token Bearer!", description = "Методы для управления списком друзей")
public interface FriendApi {

    @PutMapping("/set/{user_id}")
    @Operation(summary = "Добавление друга", description = "Добавляет пользователя в список друзей текущего пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Друг успешно добавлен"),
            @ApiResponse(responseCode = "403", description = "Необходима авторизация"),
    })
    @SecurityRequirement(name = "bearerAuth")
    ResponseEntity<String> addFriend(
            @Parameter(hidden = true) Principal principal,
            @Parameter(description = "Идентификатор пользователя, которого нужно добавить в друзья") @PathVariable("user_id") UUID friendId
    );

    @PutMapping("/delete/{user_id}")
    @Operation(summary = "Удаление друга", description = "Удаляет пользователя из списка друзей текущего пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Друг успешно удалён"),
            @ApiResponse(responseCode = "403", description = "Необходима авторизация"),
    })
    @SecurityRequirement(name = "bearerAuth")
    ResponseEntity<String> removeFriend(
            @Parameter(hidden = true) Principal principal,
            @Parameter(description = "Идентификатор пользователя, которого нужно удалить из друзей") @PathVariable("user_id") UUID friendId
    );

    @GetMapping("/get-all")
    @Operation(summary = "Получить список друзей")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "403", description = "Необходима авторизация"),
    })
    @SecurityRequirement(name = "bearerAuth")
    List<UserDto> list(@Parameter(hidden = true) Principal principal);
}
