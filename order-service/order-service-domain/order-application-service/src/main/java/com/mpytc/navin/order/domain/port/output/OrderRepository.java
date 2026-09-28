package com.mpytc.navin.order.domain.port.output;

import com.mpytc.navin.order.domain.entity.Order;

public interface OrderRepository {

    //secondary port

    Order saveOrder(Order order);

}
