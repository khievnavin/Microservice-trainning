package com.mpytc.navin.customer.domain.exception;

import com.mpytc.navin.customer.domain.exception.CustomerDomainException;

public class CustomerNotFoundException extends CustomerDomainException {

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
