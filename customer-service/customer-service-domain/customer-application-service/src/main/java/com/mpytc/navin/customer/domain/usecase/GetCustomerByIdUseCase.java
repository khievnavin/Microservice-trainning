package com.mpytc.navin.customer.domain.usecase;

import com.mpytc.navin.customer.domain.dto.CustomerResult;
import com.mpytc.navin.customer.domain.exception.CustomerDomainException;
import com.mpytc.navin.customer.domain.port.output.CustomerRepository;
import com.mpytc.navin.order.domain.valueobject.CustomerId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class GetCustomerByIdUseCase {

    private final CustomerRepository customerRepository;

    public CustomerResult execute(UUID id) {
        log.info("Executing GetCustomerByIdUseCase: {}", id);
        return customerRepository.findByIdWithCache(new CustomerId(id))
                .orElseThrow(() -> new CustomerDomainException("Customer not found"));
    }

}
