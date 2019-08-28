package com.pp.auth.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import oauth2.entity.Account;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Account account = accountDao.getAccountByUserName(username);
        System.out.println(username);
        Account account = new Account();
        account.setUsername("yupan");
        account.setPassword("123456");
        return new User(account.getUsername(), account.getPassword(),
                AuthorityUtils.createAuthorityList(account.getRoles()));
    }
}