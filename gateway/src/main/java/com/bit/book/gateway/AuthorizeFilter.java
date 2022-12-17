package com.bit.book.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain){
        // 1.获取请求参数
        ServerHttpRequest request = (ServerHttpRequest) exchange.getRequest();

        MultiValueMap<String, String> params = ((org.springframework.http.server.reactive.ServerHttpRequest) request).getQueryParams();
        // 2.获取参数中的authorization参数
        String auth = params.getFirst("authorization");
        // 3.判断参数值是否等于admin
        if ("admin".equals(auth)){
            return chain.filter(exchange);
        }
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
