package com.mpytc.navin.business.config;

import com.mpytc.navin.business.domain.service.BusinessDomainService;
import com.mpytc.navin.business.domain.service.BusinessDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfig {

    @Bean
    public BusinessDomainService businessDomainService() {
        return new BusinessDomainServiceImpl();

    }
}