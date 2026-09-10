package com.mpytc.navin.persistence.adapter;

import com.mpytc.navin.domain.entity.Order;
import com.mpytc.navin.domain.port.output.OrderRepository;
import com.mpytc.navin.persistence.repository.OrderJpaRepository;

public class OrderRepositoryAdapter implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    public OrderRepositoryAdapter(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    @Override
    public Order saveOrder(Order order){
        //Map Order to OrderEntity
        //Map OrderEntity to Order
        return null;
    }
}
