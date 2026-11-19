package com.mpytc.navin.customer.persistence.mapper;

import com.mpytc.navin.customer.domain.dto.CustomerResult;
import com.mpytc.navin.customer.persistence.entity.CustomerEntity;
import com.mpytc.navin.customer.domain.valueobject.PhoneNumber;
import com.mpytc.navin.customer.domain.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerPersistenceMapper {
    @Mapping(source="id.value", target = "id")
    @Mapping(source="email.value", target = "email")
    @Mapping(source="phoneNumber.number", target = "phoneNumber")
    CustomerEntity customerToCustomerEntity(Customer customer);


    @Mapping(source="id", target = "id.value")
    @Mapping(source="email", target = "email.value")
    @Mapping(source = "phoneNumber", target = "phoneNumber", qualifiedByName = "toPhoneNumber")
    Customer customerEntityToCustomer(CustomerEntity customerEntity);

    @Named("toPhoneNumber")
    default PhoneNumber toPhoneNumber(String phoneNumber) {
        return phoneNumber == null ? null : new PhoneNumber(phoneNumber);
    }

    @Mapping(source = "id", target = "customerId")
    CustomerResult toCustomerResult(CustomerEntity customerEntity);

    List<CustomerResult> toCustomerResultList(List<CustomerEntity> customerEntities);
}
