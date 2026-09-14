package com.mpytc.navin.order.restapi.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record OrderCeateResponse(
        UUID orderId
) {
}
