package com.atkinson.api.gateway.filter;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Objects;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    @Autowired
    Environment env;

    public static class Config{

    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {

            ServerHttpRequest request = exchange.getRequest();
            if (!request.getHeaders().containsKey(AUTHORIZATION)) {
                return onError(exchange);
            }else {

                String authorizationHeader = Objects.requireNonNull(request.getHeaders().get(AUTHORIZATION)).get(0);
                String jwtToken = authorizationHeader.replace("Bearer ", "");

                return chain.filter(exchange);
            }
        });
    }

    private Mono<Void> onError(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }

    private boolean jwtValid(String jwtToken) {
        boolean isValid = false;

        SecretKey key = Keys.hmacShaKeyFor(Base64.getEncoder()
                .encode(new String(Objects
                        .requireNonNull(env.getProperty("token.secret")).getBytes()).getBytes()));

        JwtParser parser = Jwts.parser()
                .verifyWith(key)
                .build();
        return isValid;
    }
}
