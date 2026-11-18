package com.mpytc.navin.ecommerce.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

// Same shape as RestApiErrorResponse used by the services: { code, message }
final class GatewayErrorResponses {

    private GatewayErrorResponses() {
    }

    static Map<String, Object> body(HttpStatus status, String message) {
        return Map.of(
                "code", status.getReasonPhrase(),
                "message", message
        );
    }

    static ResponseEntity<Map<String, Object>> serviceUnavailable(String service) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(body(HttpStatus.SERVICE_UNAVAILABLE, service + " is unavailable, please try again later"));
    }
}
