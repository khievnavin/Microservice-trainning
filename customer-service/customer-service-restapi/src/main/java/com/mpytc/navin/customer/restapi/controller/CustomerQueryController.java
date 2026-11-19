package com.mpytc.navin.customer.restapi.controller;

import com.mpytc.navin.core.PageResponse;
import com.mpytc.navin.customer.client.dto.UserResponse;
import com.mpytc.navin.customer.client.feign.UserFeignClient;
import com.mpytc.navin.customer.domain.dto.CustomerResult;
import com.mpytc.navin.customer.domain.dto.GetCustomersQuery;
import com.mpytc.navin.customer.domain.usecase.GetAllCustomersUseCase;
import com.mpytc.navin.customer.domain.usecase.GetCustomerByIdUseCase;
import com.mpytc.navin.customer.domain.usecase.GetCustomerByUserIdUseCase;
import com.mpytc.navin.customer.restapi.dto.CustomerResponse;
import com.mpytc.navin.customer.restapi.mapper.CustomerWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping({"/api/v1/customers", "/api/customers"})
@RequiredArgsConstructor
public class CustomerQueryController {

    private final GetCustomerByIdUseCase getCustomerByIdUseCase;
    private final GetCustomerByUserIdUseCase getCustomerByUserIdUseCase;
    private final CustomerWebMapper customerWebMapper;
    private final UserFeignClient userFeignClient;
    private final GetAllCustomersUseCase getAllCustomersUseCase;


    @GetMapping
    public PageResponse<CustomerResponse> getAll(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int size
    ){
        PageResponse<CustomerResult> result = getAllCustomersUseCase.execute(new GetCustomersQuery(page, size));
        return customerWebMapper.toCustomerResponse(result);
    }

    @GetMapping("/{id}")
    public CustomerResponse getByIdWithCache(@PathVariable UUID id){
        CustomerResult result = getCustomerByIdUseCase.execute(id);
        return customerWebMapper.toCustomerResponse(result);
    }

    @GetMapping(value = {"/user/{userId}", "/by-user/{userId}"})
    public List<CustomerResponse> getCustomerByUserId(@PathVariable String userId){
        List<CustomerResult> customerResults = getCustomerByUserIdUseCase.execute(userId);
        return customerWebMapper.toCustomerResponse(customerResults);
    }

    @GetMapping("/check-user/{userId}")
    public UserResponse checkUser(@PathVariable String userId){
        return userFeignClient.getById(userId);
    }
}
