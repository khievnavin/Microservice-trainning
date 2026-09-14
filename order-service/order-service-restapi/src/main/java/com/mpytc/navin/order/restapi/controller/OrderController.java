package com.mpytc.navin.order.restapi.controller;


import com.mpytc.navin.order.restapi.dto.OrderCeateResponse;
import com.mpytc.navin.order.restapi.dto.OrderCreateRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OrderCeateResponse createOrder(
           @Valid @RequestBody OrderCreateRequest orderCreateRequest)
    {
        return OrderCeateResponse.builder()
                .orderId(UUID.randomUUID())
                .build();
    }

}
