package com.pp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.security.web.server.savedrequest.NoOpServerRequestCache;

import com.pp.config.handler.AuthenticationFaillHandler;
import com.pp.config.handler.AuthenticationSuccessHandler;
import com.pp.config.handler.CusAuthenticationManager;
import com.pp.config.handler.CusServerSecurityContextRepository;
import com.pp.config.handler.CusServerAuthenticationEntryPoint;
import com.pp.service.CusUserDetailsService;

@EnableWebFluxSecurity
public class SecurityConfig {
    @Autowired
    private CusWebFilter webFilter;
    @Autowired
    private CusUserDetailsService userDetailsService;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFaillHandler authenticationFaillHandler;
    @Autowired
    private CusServerAuthenticationEntryPoint customHttpBasicServerAuthenticationEntryPoint;
    @Autowired
    private CusServerSecurityContextRepository serverSecurityContextRepository;

    // security的鉴权排除列表
    private static final String[] excludedAuthPages = { "/auth/login", "/auth/logout", "/health", "/api/socket/**" };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // 默认
    }

    @Bean
    public ReactiveAuthenticationManager authenticationManager() {
        return new CusAuthenticationManager(userDetailsService, passwordEncoder());
    }

    @Bean
    SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) throws Exception {
        http

                .authenticationManager(authenticationManager())

                .authorizeExchange()

                .pathMatchers(excludedAuthPages).permitAll() // 无需进行权限过滤的请求路径

                .pathMatchers(HttpMethod.OPTIONS).permitAll() // option 请求默认放行

                .anyExchange().authenticated()

                .and().httpBasic().disable()

                .formLogin().loginPage("/auth/login")
                // 认证成功
                .authenticationSuccessHandler(authenticationSuccessHandler)
                // 登陆验证失败
                .authenticationFailureHandler(authenticationFaillHandler)

                .and().exceptionHandling().authenticationEntryPoint(customHttpBasicServerAuthenticationEntryPoint) // 基于http的接口请求鉴权失败

//                .and().addFilterAt(webFilter, SecurityWebFiltersOrder.FIRST)

                .and().csrf().disable()// 必须支持跨域

                .logout().disable()

                // 禁用session
                .securityContextRepository(serverSecurityContextRepository)

        ;

        return http.build();
    }
}