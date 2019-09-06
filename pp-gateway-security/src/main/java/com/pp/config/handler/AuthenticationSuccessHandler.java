package com.pp.config.handler;

import java.util.Base64;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.WebFilterChainServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pp.entity.AuthUserDetails;

import common.result.ResultObj;
import common.result.status.ResultStatusEnum;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationSuccessHandler extends WebFilterChainServerAuthenticationSuccessHandler {

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        ServerWebExchange exchange = webFilterExchange.getExchange();
        ServerHttpResponse response = exchange.getResponse();
        // 设置headers
        HttpHeaders httpHeaders = response.getHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        // 设置body
        ResultObj<String> result = new ResultObj<>(ResultStatusEnum.SUCCESS);
        byte[] dataBytes = {};
        ObjectMapper mapper = new ObjectMapper();
        try {
            User user = (User) authentication.getPrincipal();
            AuthUserDetails userDetails = buildUser(user);
            byte[] authorization = (userDetails.getUsername() + ":" + userDetails.getPassword()).getBytes();
            String token = Base64.getEncoder().encodeToString(authorization);
            httpHeaders.add(HttpHeaders.AUTHORIZATION, token);
            userDetails.setToken(token);
            result.setData(token);
            dataBytes = mapper.writeValueAsBytes(result);
        } catch (Exception ex) {
            result.setStatus(ResultStatusEnum.AUTH_FAIL.status);
            ex.printStackTrace();
            dataBytes = "授权异常".toString().getBytes();
        }
        DataBuffer bodyDataBuffer = response.bufferFactory().wrap(dataBytes);

        return response.writeWith(Mono.just(bodyDataBuffer));
    }

    private AuthUserDetails buildUser(User user) {
        AuthUserDetails userDetails = new AuthUserDetails();
        userDetails.setUsername(user.getUsername());
        userDetails.setPassword(
                user.getPassword().substring(user.getPassword().lastIndexOf("}") + 1, user.getPassword().length()));
        return userDetails;
    }

}
