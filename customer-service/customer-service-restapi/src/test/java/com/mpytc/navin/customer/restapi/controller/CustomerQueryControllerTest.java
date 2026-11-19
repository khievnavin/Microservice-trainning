package com.mpytc.navin.customer.restapi.controller;

import com.mpytc.navin.customer.client.feign.UserFeignClient;
import com.mpytc.navin.customer.domain.dto.CustomerResult;
import com.mpytc.navin.customer.domain.usecase.GetAllCustomersUseCase;
import com.mpytc.navin.customer.domain.usecase.GetCustomerByIdUseCase;
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

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CustomerQueryControllerTest {

    @Mock
    private GetCustomerByIdUseCase getCustomerByIdUseCase;

    @Mock
    private GetCustomerByUserIdUseCase getCustomerByUserIdUseCase;

    @Mock
    private CustomerWebMapper customerWebMapper;

    @Mock
    private UserFeignClient userFeignClient;

    @Mock
    private GetAllCustomersUseCase getAllCustomersUseCase;

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

        when(getCustomerByUserIdUseCase.execute(userId)).thenReturn(List.of(customerResult));
        when(customerWebMapper.toCustomerResponse(List.of(customerResult))).thenReturn(List.of(customerResponse));

        mockMvc.perform(get("/api/v1/customers/user/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(customerId.toString()))
                .andExpect(jsonPath("$[0].userId").value(userId))
                .andExpect(jsonPath("$[0].username").value("johndoe"))
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"));

        mockMvc.perform(get("/api/customers/user/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(customerId.toString()))
                .andExpect(jsonPath("$[0].userId").value(userId));
    }

    @Test
    void getByIdWithCache_WhenCustomerExists_ShouldReturn200() throws Exception {
        UUID customerId = UUID.randomUUID();

        CustomerResult customerResult = CustomerResult.builder()
                .customerId(customerId)
                .userId("user-123")
                .username("johndoe")
                .build();

        CustomerResponse customerResponse = CustomerResponse.builder()
                .id(customerId)
                .userId("user-123")
                .username("johndoe")
                .build();

        when(getCustomerByIdUseCase.execute(customerId)).thenReturn(customerResult);
        when(customerWebMapper.toCustomerResponse(customerResult)).thenReturn(customerResponse);

        mockMvc.perform(get("/api/v1/customers/{id}", customerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(customerId.toString()))
                .andExpect(jsonPath("$.userId").value("user-123"));
    }
}
