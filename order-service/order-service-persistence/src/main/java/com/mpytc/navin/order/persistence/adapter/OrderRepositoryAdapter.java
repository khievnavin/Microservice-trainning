package com.mpytc.navin.order.persistence.adapter;

import com.mpytc.navin.order.domain.entity.Order;
import com.mpytc.navin.order.domain.port.output.OrderRepository;
import com.mpytc.navin.order.persistence.entity.OrderEntity;
import com.mpytc.navin.order.persistence.mapper.OrderPersistenceMapper;
import com.mpytc.navin.order.persistence.repository.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderPersistenceMapper orderPersistenceMapper;

    @Override
    public Order saveOrder(Order order){
        //Map Order to OrderEntity
        OrderEntity orderEntity = orderPersistenceMapper.orderToOrderEntity(order);

        if (orderEntity.getOrderAddress() != null) {
            orderEntity.getOrderAddress().setOrder(orderEntity);
        }
        if (orderEntity.getItems() != null) {
            orderEntity.getItems().forEach(orderItemEntity -> orderItemEntity.setOrder(orderEntity));
        }

        OrderEntity saveOrderEntity = orderJpaRepository.save(orderEntity);

        //Map OrderEntity to Order
        return orderPersistenceMapper.orderEntityToOrder(saveOrderEntity);
    }
}
