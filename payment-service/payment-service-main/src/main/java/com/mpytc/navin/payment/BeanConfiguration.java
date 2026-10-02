package com.mpytc.navin.payment;

import com.mpytc.navin.payment.domain.service.PaymentDomainService;
import com.mpytc.navin.payment.domain.service.PaymentDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public PaymentDomainService paymentDomainService() {
        return new PaymentDomainServiceImpl();
    }
}
