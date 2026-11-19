package com.mpytc.navin.customer.domain.usecase;

import com.mpytc.navin.customer.domain.dto.CustomerResult;
import com.mpytc.navin.customer.domain.exception.CustomerDomainException;
import com.mpytc.navin.customer.domain.port.output.CustomerRepository;
import com.mpytc.navin.customer.domain.valueobject.CustomerStatus;
import com.mpytc.navin.customer.domain.valueobject.LoyaltyTier;
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
class GetCustomerByIdUseCaseTest {

    @Mock
    private CustomerRepository customerRepository;

    private GetCustomerByIdUseCase getCustomerByIdUseCase;

    @BeforeEach
    void setUp() {
        getCustomerByIdUseCase = new GetCustomerByIdUseCase(customerRepository);
    }

    @Test
    void execute_WhenCustomerExists_ShouldReturnCustomerResult() {
        UUID customerUuid = UUID.randomUUID();
        CustomerResult customerResult = CustomerResult.builder()
                .customerId(customerUuid)
                .userId("user-123")
                .username("johndoe")
                .familyName("Doe")
                .givenName("John")
                .email("john.doe@example.com")
                .phoneNumber("012345678")
                .loyaltyTier(LoyaltyTier.SILVER)
                .status(CustomerStatus.ACTIVE)
                .build();

        when(customerRepository.findByIdWithCache(new CustomerId(customerUuid))).thenReturn(Optional.of(customerResult));

        CustomerResult result = getCustomerByIdUseCase.execute(customerUuid);

        assertNotNull(result);
        assertEquals(customerUuid, result.customerId());
        assertEquals("user-123", result.userId());
        assertEquals("johndoe", result.username());
        assertEquals("Doe", result.familyName());
        assertEquals("John", result.givenName());
        assertEquals("john.doe@example.com", result.email());
        assertEquals("012345678", result.phoneNumber());
        assertEquals(LoyaltyTier.SILVER, result.loyaltyTier());
        assertEquals(CustomerStatus.ACTIVE, result.status());

        verify(customerRepository, times(1)).findByIdWithCache(new CustomerId(customerUuid));
    }

    @Test
    void execute_WhenCustomerDoesNotExist_ShouldThrowCustomerDomainException() {
        UUID customerUuid = UUID.randomUUID();
        when(customerRepository.findByIdWithCache(new CustomerId(customerUuid))).thenReturn(Optional.empty());

        assertThrows(CustomerDomainException.class, () -> getCustomerByIdUseCase.execute(customerUuid));
        verify(customerRepository, times(1)).findByIdWithCache(new CustomerId(customerUuid));
    }
}
