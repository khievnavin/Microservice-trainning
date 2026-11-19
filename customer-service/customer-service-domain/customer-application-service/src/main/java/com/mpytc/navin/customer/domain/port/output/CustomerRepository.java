package com.mpytc.navin.customer.domain.port.output;


import com.mpytc.navin.core.PageResponse;
import com.mpytc.navin.customer.domain.dto.CustomerResult;
import com.mpytc.navin.customer.domain.entity.Customer;
import com.mpytc.navin.order.domain.valueobject.CustomerId;


import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Customer save(Customer customer);
    Optional<Customer> findById(CustomerId customerId);
    Optional<CustomerResult> findByIdWithCache(CustomerId customerId);
    List<CustomerResult> findByUserId(String userId);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUserId(String userId);

    PageResponse<CustomerResult> findAll(int page, int size);

}
