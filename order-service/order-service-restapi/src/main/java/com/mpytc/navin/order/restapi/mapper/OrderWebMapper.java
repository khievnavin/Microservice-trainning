package com.mpytc.navin.order.restapi.mapper;


import com.mpytc.navin.order.domain.dto.CreateOrderCommand;
import com.mpytc.navin.order.domain.dto.CreateOrderResult;
import com.mpytc.navin.order.restapi.dto.OrderCreateRequest;
import com.mpytc.navin.order.restapi.dto.OrderCreateResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderWebMapper {

    // Source = OrderCreateRequest
    // Target = CreateOrderCommand

    CreateOrderCommand orderCreateRequestToCreateOrderUseCase(
            OrderCreateRequest orderCreateRequest);

    OrderCreateResponse createOrderResultToOrderCreateResponse(
            CreateOrderResult createOrderResult
    );

}
