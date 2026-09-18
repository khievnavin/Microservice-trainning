package com.mpytc.navin.order.domain.port.output;

import com.mpytc.navin.order.domain.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

    Optional<Customer> findCustomer(UUID customerId);

}
