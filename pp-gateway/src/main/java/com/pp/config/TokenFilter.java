//package com.pp.config;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.pp.result.Result;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
///**
// * token拦截器
// *
// * @author David
// */
//@Component
//public class TokenFilter implements GlobalFilter, Ordered {
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Override
//    public int getOrder() {
//        return 0;
//    }
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//
//        // 检验token
//        String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
//
//        System.out.println("请求前token:" + token);
//
//        // 当token为空是就不校验token
//        if (StringUtils.isEmpty(token)) {
//            return chain.filter(exchange);
//        }
//
//        LoginAccount account = jwtUtil.getAccountFromToken(token);
//
//        if (account == null) {
//            return sendMessage(exchange, new Result(HttpStatus.UNAUTHORIZED.value(), "非法token"));
//        } else if (account.getTokenType() == TokenType.OVERDUE) {
//            return sendMessage(exchange, new Result(HttpStatus.UNAUTHORIZED.value(), "token失效，请重新登录"));
//        } else if (account.getTokenType() == TokenType.WILL_EXPIRE) {
//            // 重新获取token放入请求头中
//            ServerHttpRequest addHeader = request.mutate()
//                    .header(HttpHeaders.AUTHORIZATION, jwtUtil.generateToken(account))
//                    .build();
//            exchange = exchange.mutate().request(addHeader).build();
//
//            ServerHttpResponse response = exchange.getResponse();
//
//            // 新的token放在response头中返回给前端
//            response.getHeaders().add(HttpHeaders.AUTHORIZATION, jwtUtil.generateToken(account));
//
//            exchange = exchange.mutate().response(response).build();
//
//            System.out.println("请求变更后后token：" + addHeader.getHeaders().getFirst(HttpHeaders.AUTHORIZATION));
//        }
//
//        return chain.filter(exchange);
//    }
//
//    private Mono<Void> sendMessage(ServerWebExchange exchange, Result result) {
//        ServerHttpResponse response = exchange.getResponse();
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        byte[] datas = null;
//        try {
//            datas = objectMapper.writeValueAsString(result).getBytes();
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        DataBuffer buffer = response.bufferFactory().wrap(datas);
//
//        response.setStatusCode(HttpStatus.OK);
//        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
//
//        return response.writeWith(Mono.just(buffer));
//    }
//}