package com.mpytc.navin.customer.domain.usecase;

import com.mpytc.navin.customer.domain.dto.CustomerResult;
import com.mpytc.navin.customer.domain.dto.GetCustomerByUserIdQuery;
import com.mpytc.navin.customer.domain.exception.CustomerNotFoundException;
import com.mpytc.navin.customer.domain.mapper.CustomerDataMapper;
import com.mpytc.navin.customer.domain.port.output.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class GetCustomerByUserIdUseCase {

    private final CustomerRepository customerRepository;
    private final CustomerDataMapper customerDataMapper;

    @Transactional(readOnly = true)
    public CustomerResult execute(GetCustomerByUserIdQuery query) {
        log.info("Executing GetCustomerByUserIdUseCase : {}", query);
        return customerRepository.findByUserId(query.userId())
                .map(customerDataMapper::customerToCustomerResult)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with userId: " + query.userId()));
    }

    @Transactional(readOnly = true)
    public CustomerResult execute(String userId) {
        return execute(new GetCustomerByUserIdQuery(userId));
    }
}
