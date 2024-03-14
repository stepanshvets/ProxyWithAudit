package ru.vk.testtask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vk.testtask.dto.RegisterRequest;
import ru.vk.testtask.service.UserService;
import ru.vk.testtask.service.UsernameAlreadyExists;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest registerRequest) {
        userService.register(registerRequest.getEmail(), registerRequest.getPassword());
    }


    @ExceptionHandler(UsernameAlreadyExists.class)
    public ResponseEntity<String> handleException(UsernameAlreadyExists exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

}
