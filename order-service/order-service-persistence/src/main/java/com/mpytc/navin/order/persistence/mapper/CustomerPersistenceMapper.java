package com.mpytc.navin.order.persistence.mapper;

import com.mpytc.navin.order.domain.entity.Customer;
import com.mpytc.navin.order.persistence.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerPersistenceMapper {

    @Mapping(source = "id",target = "id.value")
    @Mapping(source = "familyName", target = "familyname")
    @Mapping(source = "givenName", target = "givename")
    Customer customerEntityToCustomer(CustomerEntity customerEntity);

}
