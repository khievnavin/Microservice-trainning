package com.mpytc.navin.customer.domain.dto;

import java.util.UUID;

public record GetCustomerQuery(
        UUID customerId
) {
}
