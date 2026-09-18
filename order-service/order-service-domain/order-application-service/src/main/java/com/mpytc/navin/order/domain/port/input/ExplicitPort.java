package com.mpytc.navin.order.domain.port.input;

import com.mpytc.navin.order.domain.dto.CreateOrderCommand;

public interface ExplicitPort {

    //primary port

    void excute(CreateOrderCommand createOrderCommand);


}
