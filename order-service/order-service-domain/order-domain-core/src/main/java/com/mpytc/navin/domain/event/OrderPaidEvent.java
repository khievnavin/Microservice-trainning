package com.mpytc.navin.domain.event;

import com.mpytc.navin.domain.entity.Order;

import java.time.ZonedDateTime;

public class OrderPaidEvent extends OrderEvent{
    public OrderPaidEvent(Order order, ZonedDateTime createdAt){
        super(order, createdAt);
    }
}
