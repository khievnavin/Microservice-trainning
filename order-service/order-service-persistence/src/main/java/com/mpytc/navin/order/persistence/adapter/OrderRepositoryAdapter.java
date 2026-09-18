package com.mpytc.navin.order.persistence.adapter;

import com.mpytc.navin.order.domain.entity.Order;
import com.mpytc.navin.order.domain.port.output.OrderRepository;
import com.mpytc.navin.order.persistence.repository.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    @Override
    public Order saveOrder(Order order){
        //Map Order to OrderEntity
        //Map OrderEntity to Order
        return null;
    }
}
