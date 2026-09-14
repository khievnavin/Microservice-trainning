package com.mpytc.navin.domain.port.input;

import com.mpytc.navin.domain.dto.CreateOrderRequest;

public interface CreateOrderUsecase {

    //primary port

    void excute(CreateOrderRequest createOrderRequest);


}
