package com.mpytc.navin.order.restapi.dto;

public record FieldErrorResponse(
        String field,
        String code,
        String reason
) {
}
