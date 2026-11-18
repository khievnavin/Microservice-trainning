package com.mpytc.navin.customer.domain.usecase;

import com.mpytc.navin.customer.domain.dto.CustomerResult;
import com.mpytc.navin.customer.domain.entity.Customer;
import com.mpytc.navin.customer.domain.exception.CustomerNotFoundException;
import com.mpytc.navin.customer.domain.mapper.CustomerDataMapper;
import com.mpytc.navin.customer.domain.port.output.CustomerRepository;
import com.mpytc.navin.customer.domain.valueobject.CustomerStatus;
import com.mpytc.navin.customer.domain.valueobject.Email;
import com.mpytc.navin.customer.domain.valueobject.LoyaltyTier;
import com.mpytc.navin.customer.domain.valueobject.PhoneNumber;
import com.mpytc.navin.order.domain.valueobject.CustomerId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetCustomerByUserIdUseCaseTest {

    @Mock
    private CustomerRepository customerRepository;

    private CustomerDataMapper customerDataMapper;
    private GetCustomerByUserIdUseCase getCustomerByUserIdUseCase;

    @BeforeEach
    void setUp() {
        customerDataMapper = new CustomerDataMapper();
        getCustomerByUserIdUseCase = new GetCustomerByUserIdUseCase(customerRepository, customerDataMapper);
    }

    @Test
    void execute_WhenCustomerExists_ShouldReturnCustomerResult() {
        String userId = "user-123";
        UUID customerUuid = UUID.randomUUID();
        Customer customer = Customer.builder()
                .id(new CustomerId(customerUuid))
                .userId(userId)
                .username("johndoe")
                .familyName("Doe")
                .givenName("John")
                .email(new Email("john.doe@example.com"))
                .phoneNumber(new PhoneNumber("012345678"))
                .loyaltyTier(LoyaltyTier.SILVER)
                .status(CustomerStatus.ACTIVE)
                .build();

        when(customerRepository.findByUserId(userId)).thenReturn(Optional.of(customer));

        CustomerResult result = getCustomerByUserIdUseCase.execute(userId);

        assertNotNull(result);
        assertEquals(customerUuid, result.customerId());
        assertEquals(userId, result.userId());
        assertEquals("johndoe", result.username());
        assertEquals("Doe", result.familyName());
        assertEquals("John", result.givenName());
        assertEquals("john.doe@example.com", result.email());
        assertEquals("012345678", result.phoneNumber());
        assertEquals(LoyaltyTier.SILVER, result.loyaltyTier());
        assertEquals(CustomerStatus.ACTIVE, result.status());

        verify(customerRepository, times(1)).findByUserId(userId);
    }

    @Test
    void execute_WhenCustomerDoesNotExist_ShouldThrowCustomerNotFoundException() {
        String userId = "non-existent-user";
        when(customerRepository.findByUserId(userId)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> getCustomerByUserIdUseCase.execute(userId));
        verify(customerRepository, times(1)).findByUserId(userId);
    }
}
