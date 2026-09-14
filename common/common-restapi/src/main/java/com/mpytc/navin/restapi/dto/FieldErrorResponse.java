package com.mpytc.navin.restapi.dto;

public record FieldErrorResponse(
        String field,
        String code,
        String reason
) {
}
