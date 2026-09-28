package com.mpytc.navin.order.restapi.mapper;

import com.mpytc.navin.order.domain.entity.Customer;
import com.mpytc.navin.order.restapi.dto.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerWebMapper {

    @Mapping(source = "id.value", target = "id")
    @Mapping(source = "familyname", target = "familyName")
    @Mapping(source = "givename", target = "givenName")
    CustomerResponse customerToCustomerResponse(Customer customer);

}
