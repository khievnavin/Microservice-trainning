package com.mpytc.navin.customer.domain.mapper;

import com.mpytc.navin.customer.domain.dto.CreateCustomerCommand;
import com.mpytc.navin.customer.domain.dto.CustomerResult;
import com.mpytc.navin.customer.domain.valueobject.Email;
import com.mpytc.navin.customer.domain.valueobject.PhoneNumber;
import com.mpytc.navin.customer.domain.entity.Customer;
import org.springframework.stereotype.Component;


@Component
public class CustomerDataMapper {

    // id and status are not set here: the domain sets them in initiateCustomer()
    public Customer createCustomerCommandToCustomer(CreateCustomerCommand createCustomerCommand) {
        return Customer.builder()
                .username(createCustomerCommand.username())
                .familyName(createCustomerCommand.familyName())
                .givenName(createCustomerCommand.givenName())
                .email(new Email(createCustomerCommand.email()))
                .phoneNumber(toPhoneNumber(createCustomerCommand.phoneNumber()))
                .build();
    }

    public PhoneNumber toPhoneNumber(String phoneNumber) {
        return phoneNumber == null ? null : new PhoneNumber(phoneNumber);
    }

    public CustomerResult customerToCustomerResult(Customer customer) {
        return new CustomerResult(
                customer.getId().value(),
                customer.getUsername(),
                customer.getFamilyName(),
                customer.getGivenName(),
                customer.getEmail() == null ? null : customer.getEmail().value(),
                customer.getPhoneNumber() == null ? null : customer.getPhoneNumber().number(),
                customer.getLoyaltyTier(),
                customer.getStatus());
    }
}
