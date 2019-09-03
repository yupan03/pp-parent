package com.pp.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO 从数据库中获取用户
        CusUserDetails user = new CusUserDetails("yupan03", "123456", false, null);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        } else if (user.isEnabled()) {
            throw new UsernameNotFoundException("用户已禁用");
        } else {
            return user;
        }
    }
}