package com.mpytc.navin.order.restapi.controller;


import com.mpytc.navin.order.domain.dto.CreateOrderCommand;
import com.mpytc.navin.order.domain.dto.CreateOrderResult;
import com.mpytc.navin.order.restapi.dto.OrderCreateRequest;
import com.mpytc.navin.order.restapi.dto.OrderCreateResponse;
import com.mpytc.navin.order.restapi.mapper.OrderWebMapper;
import com.mpytc.navin.order.usecase.CreateOrderUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderCommandController {

    //Declare dependency
    private final CreateOrderUseCase createOrderUseCase;
    private final OrderWebMapper orderWebMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OrderCreateResponse createOrder(
            @Valid @RequestBody OrderCreateRequest orderCreateRequest) {

        //Mapping Case
        CreateOrderCommand createOrderCommand = orderWebMapper
                .orderCreateRequestToCreateOrderUseCase(orderCreateRequest);

        //UseCase
        CreateOrderResult createOrderResult = createOrderUseCase.execute(createOrderCommand);

        return orderWebMapper.createOrderResultToOrderCreateResponse(createOrderResult);
    }

}
