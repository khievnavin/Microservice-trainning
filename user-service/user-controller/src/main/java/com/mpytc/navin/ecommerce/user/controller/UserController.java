package com.mpytc.navin.ecommerce.user.controller;


import com.mpytc.navin.core.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mpytc.navin.ecommerce.user.dto.UserCreateRequest;
import mpytc.navin.ecommerce.user.dto.UserResponse;
import mpytc.navin.ecommerce.user.dto.UserUpdateRequest;
import mpytc.navin.ecommerce.user.service.UserService;
import org.springframework.data.domain.PageRequest;
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

    @PatchMapping("/{id}")
    public UserResponse update(@PathVariable String id, @RequestBody UserUpdateRequest userUpdateRequest){
        return  userService.update(id,userUpdateRequest);
    }

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable String id){
        return  userService.getById(id);
    }

    @GetMapping("/username/{username}")
    public UserResponse getByUsername(@PathVariable String username){
        return  userService.getByUsername(username);
    }

    @GetMapping("/roles/{roleName}")
    public PageResponse<UserResponse> getUserByRoleName(@PathVariable String roleName, @RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                        @RequestParam(defaultValue = "15", required = false) int pageSize){
        return userService.getUsersByRoleName(roleName, PageRequest.of(pageNumber, pageSize));
    }

    @GetMapping
    public PageResponse<UserResponse> getUsers(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                               @RequestParam(defaultValue = "15", required = false) int pageSize){
        return userService.getUsers(PageRequest.of(pageNumber,pageSize));
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id){
        userService.deleteById(id);
    }


}
