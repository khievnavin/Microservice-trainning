package com.mpytc.navin.customer.restapi.controller;

import com.mpytc.navin.customer.client.dto.UserResponse;
import com.mpytc.navin.customer.client.feign.UserFeignClient;
import com.mpytc.navin.customer.domain.dto.CustomerResult;
import com.mpytc.navin.customer.domain.usecase.GetCustomerByUserIdUseCase;
import com.mpytc.navin.customer.restapi.dto.CustomerResponse;
import com.mpytc.navin.customer.restapi.mapper.CustomerWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/v1/customers", "/api/customers"})
@RequiredArgsConstructor
public class CustomerQueryController {

    private final GetCustomerByUserIdUseCase getCustomerByUserIdUseCase;
    private final CustomerWebMapper customerWebMapper;
    private final UserFeignClient userFeignClient;

    @GetMapping
    public CustomerResponse getAll(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int size
    ){

        return null;
    }

    @GetMapping(value = {"/user/{userId}", "/by-user/{userId}"})
    public CustomerResponse getCustomerByUserId(@PathVariable String userId){
        CustomerResult customerResult = getCustomerByUserIdUseCase.execute(userId);
        return customerWebMapper.customerResultToCustomerResponse(customerResult);
    }

    @GetMapping("/check-user/{userId}")
    public UserResponse checkUser(@PathVariable String userId){
        return userFeignClient.getById(userId);
    }
}
