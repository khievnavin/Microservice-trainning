package com.mpytc.navin.customer.domain.service;

import com.mpytc.navin.customer.domain.valueobject.Email;
import com.mpytc.navin.customer.domain.valueobject.PhoneNumber;
import com.mpytc.navin.customer.domain.entity.Customer;
import com.mpytc.navin.customer.domain.event.CustomerCreatedEvent;
import com.mpytc.navin.customer.domain.event.CustomerDeactivatedEvent;
import com.mpytc.navin.customer.domain.event.CustomerUpdatedEvent;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class CustomerDomainServiceImpl implements CustomerDomainService{

    @Override
    public CustomerCreatedEvent validateAndInitiateCustomer(Customer customer) {
        customer.validateCustomer();
        customer.initiateCustomer();
        return new CustomerCreatedEvent(customer, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public CustomerUpdatedEvent updateCustomer(Customer customer, String familyName, String givenName,
                                               Email email, PhoneNumber phoneNumber) {
        customer.updateCustomer(familyName, givenName, email, phoneNumber);
        return new CustomerUpdatedEvent(customer, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public CustomerDeactivatedEvent deactivateCustomer(Customer customer) {
        customer.deactivateCustomer();
        return new CustomerDeactivatedEvent(customer.getId(), ZonedDateTime.now(ZoneId.of("UTC")));
    }

}
