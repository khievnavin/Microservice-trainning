package com.mpytc.navin.order.domain.service;

import com.mpytc.navin.order.domain.entity.Business;
import com.mpytc.navin.order.domain.entity.Order;
import com.mpytc.navin.order.domain.entity.Product;
import com.mpytc.navin.order.domain.event.OrderCancelledEvent;
import com.mpytc.navin.order.domain.event.OrderCreatedEvent;
import com.mpytc.navin.order.domain.event.OrderPaidEvent;
import com.mpytc.navin.order.domain.exception.OrderDomainException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class OrderDomainServiceImpl implements OrderDomainService{
    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Business business) {
        // Validate business
        if (!business.isActive()){
            throw  new OrderDomainException("Business is not currently active");
        }

        //Set Order product information
        order.getItems().forEach(orderItem -> {
            business.getProducts().forEach(businessProduct -> {
                Product currentProduct = orderItem.getProduct();
                if(businessProduct.getId().equals(currentProduct.getId())){
                    currentProduct.updateConfirmNameAndPrice(businessProduct.getName(), businessProduct.getPrice());
                }
            });
        });

        order.validateOrder();
        order.initializeOrder();

        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {
        order.pay();
        return new OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public void approveOrder(Order order) {
        order.approve();

    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        order.initCancel(failureMessages);
        return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {
        order.cancel(failureMessages);
    }

}

