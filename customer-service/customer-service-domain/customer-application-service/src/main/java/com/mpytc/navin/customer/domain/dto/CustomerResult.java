package com.mpytc.navin.customer.domain.dto;

import com.mpytc.navin.customer.domain.valueobject.CustomerStatus;
import com.mpytc.navin.customer.domain.valueobject.LoyaltyTier;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CustomerResult(
        UUID customerId,
        String userId,
        String username,
        String familyName,
        String givenName,
        String email,
        String phoneNumber,
        LoyaltyTier loyaltyTier,
        CustomerStatus status
) {
}
