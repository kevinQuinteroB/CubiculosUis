package com.masa.api_gateway.filters;

import com.masa.api_gateway.service.AuthService;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.io.Serializable;
import java.time.Duration;
import java.util.List;

@Component
public class AuthenticationFilter
        extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final AuthService authService;

    public AuthenticationFilter(
            AuthService authService
    ) {
        super(Config.class);
        this.authService = authService;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String token = extractToken(exchange);

            if (token == null) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }


            // Validar con auth-service si no está en caché
            return authService.validateToken(token).flatMap(response -> {
                if (response.isValid()) {
                    System.out.println("Using fresh data");
                    if (hasRequiredRole(response.getRole(), config.roles)) {
                        // Agregar headers a la solicitud
                        exchange.getRequest().mutate()
                                .header("X-User-ID", String.valueOf(response.getUserId()))
                                .header("X-User-Role", response.getRole());
                        return chain.filter(exchange);
                    } else {
                        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                        return exchange.getResponse().setComplete();
                    }
                } else {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }
            });
        };
    }
    private boolean hasRequiredRole(String userRole, List<String> acceptedRoles) {
        System.out.println("User role: " + userRole);
        System.out.println("Accepted roles: " + acceptedRoles + "hola");
        return acceptedRoles.contains(userRole);
    }

    private String extractToken(ServerWebExchange exchange) {
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    record Config(List<String> roles) {}

    record UserCache(long userId, String role, long expiration) implements Serializable {}
}
