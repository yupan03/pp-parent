package com.pp.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
public class WebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationTokenFilter tokenFilter;
    @Autowired
    private UserDetailsServiceImpl userDetailsServive;
    @Autowired
    private AuthenticationFailEntryPoint authenticationFailEntryPoint;
    @Autowired
    private CusAccessDeniedhandler cusAccessDeniedhandler;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity

                .csrf().disable()
                // 禁用httpBasic认证
                .httpBasic().disable()
                // 禁用表单认证
                .formLogin().disable()

                .exceptionHandling()
                // 认证失败处理
                .authenticationEntryPoint(authenticationFailEntryPoint)
                // 权限不足处理
                .accessDeniedHandler(cusAccessDeniedhandler)

                .and().addFilterAfter(tokenFilter, UsernamePasswordAuthenticationFilter.class).authorizeRequests()

                .antMatchers("/doc.html").permitAll()

                .antMatchers("/swagger-resources/**").permitAll()

                .antMatchers("/images/**").permitAll()

                .antMatchers("/webjars/**").permitAll()

                .antMatchers("/v2/*").permitAll()

                .antMatchers("/configuration/*").permitAll()

                // 对于获取token的rest api要允许匿名访问
                .antMatchers("/token").permitAll()

                // 所有请求需要身份认证
                .anyRequest().authenticated()
                // 禁用Session
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 禁用缓存
        httpSecurity.headers().cacheControl();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServive).passwordEncoder(passwordEncoder());
    }
}