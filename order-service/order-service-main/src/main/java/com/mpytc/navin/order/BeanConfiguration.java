package com.mpytc.navin.order;

import com.mpytc.navin.order.domain.service.OrderDomainService;
import com.mpytc.navin.order.domain.service.OrderDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//How to configure beans:
//1. Annotation-based configuration
//2. Java-based configuration (method)
@Configuration
public class BeanConfiguration {

    @Bean
    public OrderDomainService orderDomainService(){
        return new OrderDomainServiceImpl();
    }

}
