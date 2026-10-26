package com.mpytc.navin.order.restapi.exception;

import org.springframework.http.HttpStatus;

public class GlobalException extends RuntimeException{

    private final HttpStatus status;

    public HttpStatus getStatus() {
        return status;
    }

    public GlobalException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}
