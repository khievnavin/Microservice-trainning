package com.mpytc.navin.customer.domain.event;

import com.mpytc.navin.order.domain.event.DomainEvent;
import com.mpytc.navin.customer.domain.entity.Customer;

public abstract class CustomerEvent implements DomainEvent<Customer> {
    private final Customer customer;

    public CustomerEvent(Customer customer){
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

}
