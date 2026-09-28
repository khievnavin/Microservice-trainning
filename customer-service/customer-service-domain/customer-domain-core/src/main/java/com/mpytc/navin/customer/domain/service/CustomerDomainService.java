package com.mpytc.navin.customer.domain.service;

import com.mpytc.navin.customer.domain.valueobject.Email;
import com.mpytc.navin.customer.domain.valueobject.PhoneNumber;
import com.mpytc.navin.customer.domain.entity.Customer;
import com.mpytc.navin.customer.domain.event.CustomerCreatedEvent;
import com.mpytc.navin.customer.domain.event.CustomerDeactivatedEvent;
import com.mpytc.navin.customer.domain.event.CustomerUpdatedEvent;


public interface CustomerDomainService {
    CustomerCreatedEvent validateAndInitiateCustomer(Customer customer);

    CustomerUpdatedEvent updateCustomer(Customer customer, String familyName, String givenName,
                                        Email email, PhoneNumber phoneNumber);

    CustomerDeactivatedEvent deactivateCustomer(Customer customer);
}
