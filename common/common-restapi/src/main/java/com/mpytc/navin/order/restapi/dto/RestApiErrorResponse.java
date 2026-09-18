package com.mpytc.navin.order.restapi.dto;

import lombok.Builder;

@Builder
public record RestApiErrorResponse<T>(
        String code,
        String message,
        T detail

) {
}
