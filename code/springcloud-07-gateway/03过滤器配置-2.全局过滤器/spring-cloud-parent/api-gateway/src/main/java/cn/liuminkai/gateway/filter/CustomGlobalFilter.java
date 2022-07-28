package cn.liuminkai.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    /**
     * 处理逻辑
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("global filter ...");
        return chain.filter(exchange); // 放行
    }

    /**
     * 过滤器执行顺序，越小越靠前
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
