package com.mpytc.navin.ecommerce.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.net.ConnectException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

// Gateway-side counterpart of the services' @RestControllerAdvice handlers.
// Handles errors that happen while the gateway proxies a request, e.g. the
// downstream service is still registered in Nacos but no longer reachable.
@Slf4j
@Component
@Order(-2) // run before Spring Boot's DefaultErrorWebExceptionHandler (-1)
public class GatewayExceptionHandler implements WebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        if (response.isCommitted()) {
            return Mono.error(ex);
        }

        HttpStatus status;
        String message;
        if (hasCause(ex, ConnectException.class)) {
            status = HttpStatus.SERVICE_UNAVAILABLE;
            message = "Downstream service is unavailable, please try again later";
        } else if (hasCause(ex, TimeoutException.class)) {
            status = HttpStatus.GATEWAY_TIMEOUT;
            message = "Downstream service did not respond in time";
        } else if (ex instanceof ResponseStatusException rse) {
            // includes gateway NotFoundException ("Unable to find instance for ...") -> 503
            HttpStatus resolved = HttpStatus.resolve(rse.getStatusCode().value());
            status = resolved != null ? resolved : HttpStatus.INTERNAL_SERVER_ERROR;
            message = rse.getReason() != null ? rse.getReason() : status.getReasonPhrase();
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "Unexpected gateway error";
        }

        log.warn("Gateway error on {} {}: {}", exchange.getRequest().getMethod(),
                exchange.getRequest().getPath(), ex.toString());

        // Same shape as RestApiErrorResponse used by the services: { code, message }
        String json = "{\"code\":\"" + escape(status.getReasonPhrase())
                + "\",\"message\":\"" + escape(message) + "\"}";

        response.setStatusCode(status);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        DataBuffer buffer = response.bufferFactory().wrap(json.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }

    private static boolean hasCause(Throwable ex, Class<? extends Throwable> type) {
        for (Throwable t = ex; t != null; t = t.getCause()) {
            if (type.isInstance(t)) return true;
        }
        return false;
    }

    private static String escape(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
