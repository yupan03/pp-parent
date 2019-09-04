package com.pp.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pp.dao.UserDao;
import com.pp.entity.table.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao UserDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(User::getAccount, username);
        User user = UserDao.selectOne(wrapper);

        // TODO 从数据库中获取用户
        if (user == null) {
            throw new BadCredentialsException("用户不存在");
        } else if (!user.getEnable()) {
            throw new BadCredentialsException("用户已禁用");
        } else {
            return new CusUserDetails(user.getAccount(), new BCryptPasswordEncoder().encode(user.getPassword()),
                    user.getEnable(), AuthorityUtils.NO_AUTHORITIES);
        }
    }
}