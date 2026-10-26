package com.mpytc.navin.ecommerce.user.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mpytc.navin.ecommerce.user.dto.UserCreateRequest;
import mpytc.navin.ecommerce.user.dto.UserResponse;
import mpytc.navin.ecommerce.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public UserResponse create(@Valid @RequestBody UserCreateRequest userCreateRequest){
        return  userService.create(userCreateRequest);
    }
}
