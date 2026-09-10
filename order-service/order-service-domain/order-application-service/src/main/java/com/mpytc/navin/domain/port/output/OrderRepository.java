package com.mpytc.navin.domain.port.output;

import com.mpytc.navin.domain.entity.Order;

public interface OrderRepository {

    Order saveOrder(Order order);

}
