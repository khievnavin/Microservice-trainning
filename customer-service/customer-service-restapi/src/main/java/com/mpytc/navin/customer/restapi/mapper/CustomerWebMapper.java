package com.mpytc.navin.customer.restapi.mapper;

import com.mpytc.navin.core.PageResponse;
import com.mpytc.navin.customer.domain.dto.CreateCustomerCommand;
import com.mpytc.navin.customer.domain.dto.CreateCustomerResult;
import com.mpytc.navin.customer.domain.dto.CustomerResult;
import com.mpytc.navin.customer.domain.dto.UpdateCustomerCommand;
import com.mpytc.navin.customer.domain.dto.UpdateCustomerResult;
import com.mpytc.navin.customer.restapi.dto.CustomerCreateRequest;
import com.mpytc.navin.customer.restapi.dto.CustomerCreateResponse;
import com.mpytc.navin.customer.restapi.dto.CustomerResponse;
import com.mpytc.navin.customer.restapi.dto.CustomerUpdateRequest;
import com.mpytc.navin.customer.restapi.dto.CustomerUpdateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CustomerWebMapper {
    @Mapping(source = "customerId", target = "id")
    CustomerResponse toCustomerResponse(CustomerResult customerResult);

    List<CustomerResponse> toCustomerResponse(List<CustomerResult> customerResults);

    CreateCustomerCommand customerCreateRequestToCreateCustomerCommand(CustomerCreateRequest customerCreateRequest);

    CustomerCreateResponse createCustomerResultToCustomerCreateResponse(CreateCustomerResult createCustomerResult);

    @Mapping(source = "customerId", target = "customerId")
    UpdateCustomerCommand customerUpdateRequestToUpdateCustomerCommand(UUID customerId, CustomerUpdateRequest customerUpdateRequest);

    CustomerUpdateResponse updateCustomerResultToCustomerUpdateResponse(UpdateCustomerResult updateCustomerResult);

    PageResponse<CustomerResponse> toCustomerResponse(PageResponse<CustomerResult> result);
}
