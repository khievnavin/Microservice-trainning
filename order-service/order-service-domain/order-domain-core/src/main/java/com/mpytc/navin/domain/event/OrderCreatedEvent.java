package com.mpytc.navin.domain.event;

import com.mpytc.navin.domain.entity.Order;

import java.time.ZonedDateTime;

public class OrderCreatedEvent extends OrderEvent{
    public OrderCreatedEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);    }
}
