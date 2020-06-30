package com.pp.service;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class JsonBlockExceptionHandler extends SentinelGatewayBlockExceptionHandler {

    public JsonBlockExceptionHandler(List<ViewResolver> viewResolvers, ServerCodecConfigurer serverCodecConfigurer) {
        super(viewResolvers, serverCodecConfigurer);
    }

    private Mono<Void> writeResponse(String result, ServerWebExchange exchange) {
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        serverHttpResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        byte[] datas = result.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = serverHttpResponse.bufferFactory().wrap(datas);
        return serverHttpResponse.writeWith(Mono.just(buffer));
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        if (exchange.getResponse().isCommitted()) {
            return Mono.error(ex);
        } else {
            return !BlockException.isBlockException(ex) ? Mono.error(ex) :
                    this.handleBlockedRequest(exchange, ex).flatMap((response) -> {
                        String msg = "";
                        if (ex instanceof FlowException) {
                            msg = "{\"status\":429,\"msg\":\"当前人数过多，请稍后重试!\"}";
                        } else if (ex instanceof ParamFlowException) {
                            msg = "{\"status\":429,\"msg\":\"当前人数过多，请稍后重试!\"}";
                        } else if (ex instanceof DegradeException) {
                            msg = "{\"status\":403,\"msg\":\"系统维护中，请稍后重试!\"}";
                        } else if (ex instanceof AuthorityException) {
                            msg = "{\"status\":403,\"msg\":\"黑白名单!\"}";
                        } else if (ex instanceof SystemBlockException) {
                            msg = "{\"status\":403,\"msg\":\"系统规则!\"}";
                        } else {
                            msg = "{\"status\":500,\"msg\":\"服务器错误\"}";
                        }
                        return this.writeResponse(msg, exchange);
                    });
        }
    }

    private Mono<ServerResponse> handleBlockedRequest(ServerWebExchange exchange, Throwable throwable) {
        return GatewayCallbackManager.getBlockHandler().handleRequest(exchange, throwable);
    }
}
