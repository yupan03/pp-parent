package com.pp.config.security;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableAutoConfiguration
public class WebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {
    @Resource
    private JwtAuthenticationTokenFilter tokenFilter;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().exceptionHandling() // 配置401

                .and().addFilterAfter(tokenFilter, UsernamePasswordAuthenticationFilter.class).authorizeRequests()

                .antMatchers("/swagger-ui.html").permitAll().antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/images/**").permitAll().antMatchers("/webjars/**").permitAll().antMatchers("/v2/*")
                .permitAll().antMatchers("/configuration/*").permitAll()
                // 对于获取token的rest api要允许匿名访问
                .antMatchers("/user/eLogin/**").permitAll().antMatchers("/user/login/**").permitAll()

                // 过滤通用业务系统的调用
                .antMatchers("/companySign/cbasCheckCompanySign/**").permitAll()
                .antMatchers("/companySign/listAllApplyCompanySign/**").permitAll()
                .antMatchers("/companySign/applyCompanySign/**").permitAll()

                // 下载文件
                .antMatchers("/files/downLoadFile/**").permitAll().antMatchers("/files/downLoadUpFile/**").permitAll()

                .antMatchers("/companySign/leaderPassCompanySign/**").permitAll()
                .antMatchers("/companySign/leaderRefuseCompanySign/**").permitAll()

                // 所有请求需要身份认证
                .anyRequest().authenticated().and()
                // 禁用Session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 禁用缓存
        httpSecurity.headers().cacheControl();
    }
}
