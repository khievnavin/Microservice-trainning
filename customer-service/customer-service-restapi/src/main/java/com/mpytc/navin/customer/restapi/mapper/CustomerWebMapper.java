package com.mpytc.navin.customer.restapi.mapper;

import com.mpytc.navin.customer.domain.dto.CreateCustomerCommand;
import com.mpytc.navin.customer.domain.dto.CreateCustomerResult;
import com.mpytc.navin.customer.domain.dto.UpdateCustomerCommand;
import com.mpytc.navin.customer.domain.dto.UpdateCustomerResult;
import com.mpytc.navin.customer.restapi.dto.CustomerCreateRequest;
import com.mpytc.navin.customer.restapi.dto.CustomerCreateResponse;
import com.mpytc.navin.customer.restapi.dto.CustomerUpdateRequest;
import com.mpytc.navin.customer.restapi.dto.CustomerUpdateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CustomerWebMapper {
    CreateCustomerCommand customerCreateRequestToCreateCustomerCommand(CustomerCreateRequest customerCreateRequest);
    CustomerCreateResponse createCustomerResultToCustomerCreateResponse(CreateCustomerResult createCustomerResult);

    // customerId comes from the path, the rest from the request body
    @Mapping(source = "customerId", target = "customerId")
    UpdateCustomerCommand customerUpdateRequestToUpdateCustomerCommand(UUID customerId, CustomerUpdateRequest customerUpdateRequest);
    CustomerUpdateResponse updateCustomerResultToCustomerUpdateResponse(UpdateCustomerResult updateCustomerResult);
}
