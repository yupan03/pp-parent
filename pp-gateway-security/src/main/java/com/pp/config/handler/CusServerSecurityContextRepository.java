package com.pp.config.handler;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.pp.service.CusUserDetailsService;

import reactor.core.publisher.Mono;

/**
 * 自定义处理请求(判断是否有API权限，拦截请求作用)
 * 
 * @author David
 *
 */
@Component
public class CusServerSecurityContextRepository implements ServerSecurityContextRepository {
    @Autowired
    private CusUserDetailsService userDetailsService;

    @Override
    public Mono<Void> save(ServerWebExchange serverWebExchange, SecurityContext securityContext) {
        return Mono.empty();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange serverWebExchange) {
        ServerHttpRequest request = serverWebExchange.getRequest();
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String authToken = authHeader.substring(7);
            System.out.println(authToken);

            // 获取登录名

            //
            UserDetails user = userDetailsService.findByUsername("yupan").block();

            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

            Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(),
                    authorities);
            return Mono.justOrEmpty(new SecurityContextImpl(auth));
        } else {
            // 提示没有权限
            return Mono.empty();
        }
    }
}