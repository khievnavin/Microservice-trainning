package com.mpytc.navin.order.domain.dto;

import com.mpytc.navin.order.domain.valueobject.OrderId;

import java.util.UUID;

public record CreateOrderResult(
        UUID orderId)
{

}
