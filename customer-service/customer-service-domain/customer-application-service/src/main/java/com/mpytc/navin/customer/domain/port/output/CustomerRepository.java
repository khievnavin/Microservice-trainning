package com.mpytc.navin.customer.domain.port.output;


import com.mpytc.navin.customer.domain.entity.Customer;
import com.mpytc.navin.order.domain.valueobject.CustomerId;


import java.util.Optional;

public interface CustomerRepository {
    Customer save(Customer customer);
    Optional<Customer> findById(CustomerId customerId);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
