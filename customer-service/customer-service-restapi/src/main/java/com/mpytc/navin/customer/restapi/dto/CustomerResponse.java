package com.mpytc.navin.customer.restapi.dto;

import com.mpytc.navin.customer.domain.valueobject.CustomerStatus;
import com.mpytc.navin.customer.domain.valueobject.LoyaltyTier;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CustomerResponse(
        UUID id,
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
