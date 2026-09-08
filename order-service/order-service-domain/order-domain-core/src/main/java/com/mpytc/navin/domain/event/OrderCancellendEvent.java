package com.mpytc.navin.domain.event;

import com.mpytc.navin.domain.entity.Order;

import java.time.ZonedDateTime;

public class OrderCancellendEvent extends OrderEvent{
    public OrderCancellendEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
