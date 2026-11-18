package com.mpytc.navin.customer.restapi.controller;

import com.mpytc.navin.customer.client.feign.UserFeignClient;
import com.mpytc.navin.customer.domain.dto.CustomerResult;
import com.mpytc.navin.customer.domain.exception.CustomerNotFoundException;
import com.mpytc.navin.customer.domain.usecase.GetCustomerByUserIdUseCase;
import com.mpytc.navin.customer.domain.valueobject.CustomerStatus;
import com.mpytc.navin.customer.domain.valueobject.LoyaltyTier;
import com.mpytc.navin.customer.restapi.dto.CustomerResponse;
import com.mpytc.navin.customer.restapi.exception.CustomerGlobalExceptionHandler;
import com.mpytc.navin.customer.restapi.mapper.CustomerWebMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CustomerQueryControllerTest {

    @Mock
    private GetCustomerByUserIdUseCase getCustomerByUserIdUseCase;

    @Mock
    private CustomerWebMapper customerWebMapper;

    @Mock
    private UserFeignClient userFeignClient;

    @InjectMocks
    private CustomerQueryController customerQueryController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerQueryController)
                .setControllerAdvice(new CustomerGlobalExceptionHandler())
                .build();
    }

    @Test
    void getCustomerByUserId_WhenCustomerExists_ShouldReturn200() throws Exception {
        String userId = "user-123";
        UUID customerId = UUID.randomUUID();

        CustomerResult customerResult = CustomerResult.builder()
                .customerId(customerId)
                .userId(userId)
                .username("johndoe")
                .familyName("Doe")
                .givenName("John")
                .email("john.doe@example.com")
                .phoneNumber("012345678")
                .loyaltyTier(LoyaltyTier.BRONZE)
                .status(CustomerStatus.ACTIVE)
                .build();

        CustomerResponse customerResponse = CustomerResponse.builder()
                .id(customerId)
                .userId(userId)
                .username("johndoe")
                .familyName("Doe")
                .givenName("John")
                .email("john.doe@example.com")
                .phoneNumber("012345678")
                .loyaltyTier(LoyaltyTier.BRONZE)
                .status(CustomerStatus.ACTIVE)
                .build();

        when(getCustomerByUserIdUseCase.execute(userId)).thenReturn(customerResult);
        when(customerWebMapper.customerResultToCustomerResponse(any(CustomerResult.class))).thenReturn(customerResponse);

        mockMvc.perform(get("/api/v1/customers/user/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(customerId.toString()))
                .andExpect(jsonPath("$.userId").value(userId))
                .andExpect(jsonPath("$.username").value("johndoe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));

        mockMvc.perform(get("/api/customers/user/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(customerId.toString()))
                .andExpect(jsonPath("$.userId").value(userId));
    }

    @Test
    void getCustomerByUserId_WhenCustomerNotFound_ShouldReturn404() throws Exception {
        String userId = "non-existent-user";

        when(getCustomerByUserIdUseCase.execute(userId))
                .thenThrow(new CustomerNotFoundException("Customer not found with userId: " + userId));

        mockMvc.perform(get("/api/v1/customers/user/{userId}", userId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("Not Found"))
                .andExpect(jsonPath("$.message").value("Customer not found with userId: " + userId));
    }
}
