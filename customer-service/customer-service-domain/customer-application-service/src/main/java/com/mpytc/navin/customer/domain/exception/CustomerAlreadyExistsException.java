package com.mpytc.navin.customer.domain.exception;

import com.mpytc.navin.customer.domain.exception.CustomerDomainException;

public class CustomerAlreadyExistsException extends CustomerDomainException {

    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}
