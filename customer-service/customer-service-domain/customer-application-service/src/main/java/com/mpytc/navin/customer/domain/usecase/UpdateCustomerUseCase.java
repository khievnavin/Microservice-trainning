package com.mpytc.navin.customer.domain.usecase;

import com.mpytc.navin.customer.domain.dto.UpdateCustomerCommand;
import com.mpytc.navin.customer.domain.dto.UpdateCustomerResult;
import com.mpytc.navin.customer.domain.exception.CustomerAlreadyExistsException;
import com.mpytc.navin.customer.domain.exception.CustomerNotFoundException;
import com.mpytc.navin.customer.domain.mapper.CustomerDataMapper;
import com.mpytc.navin.customer.domain.port.output.CustomerRepository;
import com.mpytc.navin.order.domain.valueobject.CustomerId;
import com.mpytc.navin.customer.domain.valueobject.Email;
import com.mpytc.navin.customer.domain.entity.Customer;
import com.mpytc.navin.customer.domain.event.CustomerUpdatedEvent;
import com.mpytc.navin.customer.domain.service.CustomerDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

// Use case: change an existing customer's name, email and phone number.
// Called by the REST API controller (PUT /api/v1/customers/{customerId}).
// Flow: load customer -> check new email -> domain updates -> save -> return id
@Component
@Slf4j
@RequiredArgsConstructor
public class UpdateCustomerUseCase {

    private final CustomerDomainService customerDomainService;
    private final CustomerRepository customerRepository;
    private final CustomerDataMapper customerDataMapper;

    @Transactional
    public UpdateCustomerResult execute(UpdateCustomerCommand updateCustomerCommand) {
        log.info("Execute UpdateCustomerUseCase : {}", updateCustomerCommand);

        Customer customer = customerRepository.findById(new CustomerId(updateCustomerCommand.customerId()))
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Customer not found: " + updateCustomerCommand.customerId()));

        Email newEmail = new Email(updateCustomerCommand.email());
        if (!newEmail.equals(customer.getEmail()) && customerRepository.existsByEmail(newEmail.value())) {
            throw new CustomerAlreadyExistsException("Email already exists");
        }

        CustomerUpdatedEvent customerUpdatedEvent = customerDomainService.updateCustomer(customer,
                updateCustomerCommand.familyName(),
                updateCustomerCommand.givenName(),
                newEmail,
                customerDataMapper.toPhoneNumber(updateCustomerCommand.phoneNumber()));
        Customer savedCustomer = customerRepository.save(customer);

        log.info("Customer updated with id: {} at {}",
                savedCustomer.getId().value(), customerUpdatedEvent.getUpdatedAt());
        return new UpdateCustomerResult(savedCustomer.getId().value());
    }
}
