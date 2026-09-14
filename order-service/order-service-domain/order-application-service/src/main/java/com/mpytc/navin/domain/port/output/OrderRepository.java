package com.mpytc.navin.domain.port.output;

import com.mpytc.navin.domain.entity.Order;

public interface OrderRepository {

    //secondary port

    Order saveOrder(Order order);

}
