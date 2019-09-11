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

import com.pp.config.handler.AuthenticationFaillHandler;
import com.pp.config.handler.AuthenticationSuccessHandler;
import com.pp.config.handler.CusAccessDenieHandler;
import com.pp.config.handler.CusAuthenticationManager;
import com.pp.config.handler.CusServerAuthenticationEntryPoint;
import com.pp.config.handler.CusServerSecurityContextRepository;
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
    @Autowired
    private CusAccessDenieHandler accessDenieHandler;

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
    public SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) throws Exception {
        http.csrf().disable()

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

                .and().exceptionHandling()

                .authenticationEntryPoint(customHttpBasicServerAuthenticationEntryPoint) // 基于http的接口请求鉴权失败

                .accessDeniedHandler(accessDenieHandler)

//                .and().addFilterAt(webFilter, SecurityWebFiltersOrder.FIRST)

                .and().logout().disable()

                // 禁用session
                .securityContextRepository(serverSecurityContextRepository)

        ;

        return http.build();
    }
}