package com.mpytc.navin.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@EntityScan(basePackages = {
        "mpytc.navin.ecommerce.user.entities"
})
@EnableJpaRepositories(basePackages =
        "mpytc.navin.ecommerce.user.repository"
)

@SpringBootApplication(scanBasePackages = {
        "com.mpytc.navin.user",
        "com.mpytc.navin.ecommerce.user",
        "mpytc.navin.ecommerce.user"
})

@EnableDiscoveryClient
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
