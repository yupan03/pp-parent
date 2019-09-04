package com.project.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.project.utils.MD5Encoder;

import reactor.core.publisher.Mono;

public class SecurityUserDetailsService implements ReactiveUserDetailsService {

    @Value("${spring.security.user.name}")
    private String userName;

    @Value("${spring.security.user.password}")
    private String password;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        System.out.println(username);
        // todo 预留调用数据库根据用户名获取用户
        if (userName.equals(username)) {
            UserDetails user = User.withUsername(userName).password(MD5Encoder.encode(password, username))
                    .roles("admin").authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("admin")).build();
            return Mono.just(user);
        } else {
            return Mono.error(new UsernameNotFoundException("User Not Found"));

        }
    }
}