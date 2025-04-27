package com.example.myapp.rest;

import com.example.myapp.dao.entity.Post;
import com.example.myapp.service.dto.PostDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Tag(name = "Управление постами. Необходим Token Bearer!", description = "Методы для работы с постами")
public interface PostApi {

    @Operation(summary = "Создание поста", description = "Создает новый пост")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно создан пост"),
            @ApiResponse(responseCode = "403", description = "Необходима авторизация"),
    })
    @SecurityRequirement(name = "bearerAuth")
    ResponseEntity<UUID> createPost(@Parameter(hidden = true) Principal principal, @RequestBody String text);

    @Operation(summary = "Обновление поста", description = "Обновляет существующий пост")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно изменен пост"),
            @ApiResponse(responseCode = "403", description = "Необходима авторизация"),
    })
    ResponseEntity<Void> updatePost(@RequestParam UUID postId, @RequestBody String text);

    @Operation(summary = "Удаление поста", description = "Удаляет пост по его идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно удален пост"),
            @ApiResponse(responseCode = "403", description = "Необходима авторизация"),
    })
    ResponseEntity<Void> deletePost(@PathVariable UUID id);

    @GetMapping("/get/{id}")
    @Operation(summary = "Получение поста", description = "Возвращает пост по его идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно получен пост"),
            @ApiResponse(responseCode = "403", description = "Необходима авторизация"),
    })
    ResponseEntity<PostDto> getPost(@PathVariable UUID id);

    @GetMapping("/feed")
    @Operation(summary = "Лента друзей", description = "Возвращает посты друзей")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно получены посты друзей"),
            @ApiResponse(responseCode = "403", description = "Необходима авторизация"),
    })
    @SecurityRequirement(name = "bearerAuth")
    ResponseEntity<List<PostDto>> getFriendsFeed(@Parameter(hidden = true) Principal principal,
                                                 @RequestParam(defaultValue = "0") int offset,
                                                 @RequestParam(defaultValue = "10") int limit);
}
