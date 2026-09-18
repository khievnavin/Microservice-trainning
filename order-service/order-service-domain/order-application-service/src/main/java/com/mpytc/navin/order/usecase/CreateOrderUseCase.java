package com.mpytc.navin.order.usecase;

import com.mpytc.navin.order.domain.dto.CreateOrderCommand;
import com.mpytc.navin.order.domain.dto.CreateOrderResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@Slf4j  //lombok: create log object when compl

public class CreateOrderUseCase {

    public CreateOrderResult execute(CreateOrderCommand createOrderCommand){
        log.info("executing createOrderUseCase:{}", createOrderCommand);

        return new CreateOrderResult(UUID.randomUUID());

    }

}

//Insert, Update ,Delete => Command - > Transaction
//Select -> Query -> Transaction read only
//Pattern: CQRS = Command Query Responsibility Segregation