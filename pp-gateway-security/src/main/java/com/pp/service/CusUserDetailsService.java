package com.pp.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CusUserDetailsService implements ReactiveUserDetailsService {

    @Value("${spring.security.user.name}")
    private String userName;

    @Value("${spring.security.user.password}")
    private String password;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        // todo 预留调用数据库根据用户名获取用户
        if (StringUtils.equals(userName, username)) {
            System.out.println("标志执行-----------------------------------------------------");
            UserDetails user = User.withUsername(userName).password("123456").roles("admin")
                    .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("admin")).build();
            return Mono.just(user);
        } else {
            return Mono.error(new UsernameNotFoundException("用户不存在"));

        }

    }

}
