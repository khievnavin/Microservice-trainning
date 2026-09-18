package com.mpytc.navin.order.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {
        "com.mpytc.navin.order.persistence.repository"
})

@EntityScan(basePackages = {
       "com.mpytc.navin.order.persistence"
})

@SpringBootApplication
public class OrderServiceApplication {
    static void main(String[] args) {
       SpringApplication.run(OrderServiceApplication.class, args);
    }
}
