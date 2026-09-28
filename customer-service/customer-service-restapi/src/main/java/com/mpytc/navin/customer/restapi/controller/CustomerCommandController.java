package com.mpytc.navin.customer.restapi.controller;


import com.mpytc.navin.customer.domain.dto.CreateCustomerCommand;
import com.mpytc.navin.customer.domain.dto.CreateCustomerResult;
import com.mpytc.navin.customer.domain.dto.DeactivateCustomerCommand;
import com.mpytc.navin.customer.domain.dto.UpdateCustomerCommand;
import com.mpytc.navin.customer.domain.dto.UpdateCustomerResult;
import com.mpytc.navin.customer.domain.usecase.CreateCustomerUseCase;
import com.mpytc.navin.customer.domain.usecase.DeactivateCustomerUseCase;
import com.mpytc.navin.customer.domain.usecase.UpdateCustomerUseCase;
import com.mpytc.navin.customer.restapi.dto.CustomerCreateRequest;
import com.mpytc.navin.customer.restapi.dto.CustomerCreateResponse;
import com.mpytc.navin.customer.restapi.dto.CustomerUpdateRequest;
import com.mpytc.navin.customer.restapi.dto.CustomerUpdateResponse;
import com.mpytc.navin.customer.restapi.mapper.CustomerWebMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerCommandController {

    private final CustomerWebMapper customerWebMapper;
    private final CreateCustomerUseCase createCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final DeactivateCustomerUseCase deactivateCustomerUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerCreateResponse createCustomer(@Valid @RequestBody CustomerCreateRequest customerCreateRequest){
        CreateCustomerCommand createCustomerCommand = customerWebMapper.customerCreateRequestToCreateCustomerCommand(customerCreateRequest);
        CreateCustomerResult createCustomerResult = createCustomerUseCase.execute(createCustomerCommand);
        return customerWebMapper.createCustomerResultToCustomerCreateResponse(createCustomerResult);
    }

    @PutMapping("/{customerId}")
    public CustomerUpdateResponse updateCustomer(@PathVariable UUID customerId,
                                                 @Valid @RequestBody CustomerUpdateRequest customerUpdateRequest){
        UpdateCustomerCommand updateCustomerCommand = customerWebMapper.customerUpdateRequestToUpdateCustomerCommand(customerId, customerUpdateRequest);
        UpdateCustomerResult updateCustomerResult = updateCustomerUseCase.execute(updateCustomerCommand);
        return customerWebMapper.updateCustomerResultToCustomerUpdateResponse(updateCustomerResult);
    }

    @PatchMapping("/{customerId}/deactivate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateCustomer(@PathVariable UUID customerId){
        deactivateCustomerUseCase.execute(new DeactivateCustomerCommand(customerId));
    }

}
