package com.mpytc.navin.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {
        "com.mpytc.navin.customer.persistence"
})
@EnableJpaRepositories(basePackages =
        "com.mpytc.navin.customer.persistence"
)
@SpringBootApplication
public class CustomerServiceApplication {
    static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
}
