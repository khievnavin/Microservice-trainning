package com.mpytc.navin.ecommerce.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import java.util.Map;

// Target of the "service-fallback" route: reached when /<name>-service/** has no
// discovery route, i.e. the service is not registered in Nacos (down).
@RestController
public class FallbackController {

    @RequestMapping("/fallback/service-unavailable")
    public ResponseEntity<Map<String, Object>> serviceUnavailable(ServerWebExchange exchange) {
        String path = exchange.getRequest().getPath().value();
        String service = path.startsWith("/") ? path.substring(1).split("/", 2)[0] : path;
        return GatewayErrorResponses.serviceUnavailable(service);
    }
}
