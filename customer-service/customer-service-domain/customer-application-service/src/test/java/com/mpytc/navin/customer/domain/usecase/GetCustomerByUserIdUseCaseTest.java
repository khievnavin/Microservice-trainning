package com.mpytc.navin.customer.domain.usecase;

import com.mpytc.navin.customer.domain.dto.CustomerResult;
import com.mpytc.navin.customer.domain.dto.GetCustomerByUserIdQuery;
import com.mpytc.navin.customer.domain.port.output.CustomerRepository;
import com.mpytc.navin.customer.domain.valueobject.CustomerStatus;
import com.mpytc.navin.customer.domain.valueobject.LoyaltyTier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetCustomerByUserIdUseCaseTest {

    @Mock
    private CustomerRepository customerRepository;

    private GetCustomerByUserIdUseCase getCustomerByUserIdUseCase;

    @BeforeEach
    void setUp() {
        getCustomerByUserIdUseCase = new GetCustomerByUserIdUseCase(customerRepository);
    }

    @Test
    void execute_WhenCustomersExist_ShouldReturnCustomerResultList() {
        String userId = "user-123";
        UUID customerUuid = UUID.randomUUID();
        CustomerResult customerResult = CustomerResult.builder()
                .customerId(customerUuid)
                .userId(userId)
                .username("johndoe")
                .familyName("Doe")
                .givenName("John")
                .email("john.doe@example.com")
                .phoneNumber("012345678")
                .loyaltyTier(LoyaltyTier.SILVER)
                .status(CustomerStatus.ACTIVE)
                .build();

        when(customerRepository.findByUserId(userId)).thenReturn(List.of(customerResult));

        List<CustomerResult> result = getCustomerByUserIdUseCase.execute(userId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(customerUuid, result.get(0).customerId());
        assertEquals(userId, result.get(0).userId());

        verify(customerRepository, times(1)).findByUserId(userId);
    }

    @Test
    void execute_WithQuery_WhenCustomersExist_ShouldReturnCustomerResultList() {
        String userId = "user-123";
        UUID customerUuid = UUID.randomUUID();
        CustomerResult customerResult = CustomerResult.builder()
                .customerId(customerUuid)
                .userId(userId)
                .build();

        when(customerRepository.findByUserId(userId)).thenReturn(List.of(customerResult));

        List<CustomerResult> result = getCustomerByUserIdUseCase.execute(new GetCustomerByUserIdQuery(userId));

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(customerRepository, times(1)).findByUserId(userId);
    }
}
