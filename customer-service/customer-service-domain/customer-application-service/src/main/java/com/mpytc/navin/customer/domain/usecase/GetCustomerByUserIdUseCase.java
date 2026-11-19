package com.mpytc.navin.customer.domain.usecase;

import com.mpytc.navin.customer.domain.dto.CustomerResult;
import com.mpytc.navin.customer.domain.dto.GetCustomerByUserIdQuery;
import com.mpytc.navin.customer.domain.port.output.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class GetCustomerByUserIdUseCase {

    private final CustomerRepository customerRepository;

    public List<CustomerResult> execute(GetCustomerByUserIdQuery query) {
        log.info("Executing GetCustomerByUserIdUseCase: {}", query);
        return customerRepository.findByUserId(query.userId());
    }

    public List<CustomerResult> execute(String userId) {
        return execute(new GetCustomerByUserIdQuery(userId));
    }
}
