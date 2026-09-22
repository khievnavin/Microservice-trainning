package com.mpytc.navin.order.restapi.exception;


import com.mpytc.navin.order.domain.exception.OrderDomainException;
import com.mpytc.navin.order.restapi.dto.RestApiErrorResponse;
import com.mpytc.navin.order.restapi.exception.GlobalExceptionHandler;
import com.mpytc.navin.persistence.business.exception.BusinessPersistenceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderGlobalExceptionHandler extends GlobalExceptionHandler {

    //TODO : write your exception handler here
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OrderDomainException.class)
    public RestApiErrorResponse<?> handleOrderDomainException(OrderDomainException e) {
        return RestApiErrorResponse.builder()
                .message(e.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessPersistenceException.class)
    public RestApiErrorResponse<?> handleOrderPersistenceException(BusinessPersistenceException e) {
        return RestApiErrorResponse.builder()
                .message(HttpStatus.BAD_REQUEST.getReasonPhrase( ))
                .message(e.getMessage())
                .build();
    }
}
