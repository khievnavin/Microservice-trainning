package com.mpytc.navin.order.restapi.dto;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record BusinessResponse(
        UUID id,
        boolean active,
        List<ProductResponse> products
) {
}
