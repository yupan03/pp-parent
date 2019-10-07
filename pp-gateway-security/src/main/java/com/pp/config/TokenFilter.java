package com.pp.config;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pp.entity.FilterApi;
import com.pp.service.FilterApiService;

import common.result.Result;
import common.result.status.ResultStatusEnum;
import jwt.JwtUtil;
import jwt.LoginAccount;
import jwt.constant.TokenType;
import reactor.core.publisher.Mono;

/**
 * token拦截器
 * 
 * @author David
 *
 */
@Component
public class TokenFilter implements GlobalFilter, Ordered {
    @Autowired
    private FilterApiService filterApiService;

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        System.out.println("------------------------");
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();

        System.out.println(path);
        // 将不需要token校验的URL进行过滤
        List<FilterApi> apis = filterApiService.list();

        for (FilterApi api : apis) {
            switch (api.getType()) {
            case 0:
                // 正常匹配
                if (path.equals(api.getUrl()))
                    return chain.filter(exchange);
                break;
            case 1:
                // 前缀
                if (path.startsWith(api.getUrl()))
                    return chain.filter(exchange);
                break;
            case 2:
                // 后缀
                if (path.endsWith(api.getUrl()))
                    return chain.filter(exchange);
                break;
            default:
                break;
            }
        }

        // 检验token
        String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        Map<String, String> singleValueMap = request.getHeaders().toSingleValueMap();
        System.out.println(singleValueMap);

        LoginAccount account = new JwtUtil().getAccountFromToken(token);

        if (account == null) {
            return sendMessage(exchange, new Result(ResultStatusEnum.AUTH_FAIL, "非法token"));
        } else if (account.getTokenType() == TokenType.OVERDUE) {
            return sendMessage(exchange, new Result(ResultStatusEnum.AUTH_FAIL, "token失效，请重新登录"));
        } else if (account.getTokenType() == TokenType.WILL_EXPIRE) {
            // 重新获取token放入请求头中
            // 隐患（当请求头中含有其他信息是是否把这些信息也放入新的请求头中）
            ServerHttpRequest addHeader = exchange.getRequest().mutate()
                    .header(HttpHeaders.AUTHORIZATION, new JwtUtil().generateToken(account)).build();
            exchange = exchange.mutate().request(addHeader).build();
        }

        return chain.filter(exchange);
    }

    private Mono<Void> sendMessage(ServerWebExchange exchange, Result result) {
        ServerHttpResponse response = exchange.getResponse();

        ObjectMapper objectMapper = new ObjectMapper();

        byte[] datas = null;
        try {
            datas = objectMapper.writeValueAsString(result).getBytes();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        DataBuffer buffer = response.bufferFactory().wrap(datas);

        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");

        return response.writeWith(Mono.just(buffer));
    }
}