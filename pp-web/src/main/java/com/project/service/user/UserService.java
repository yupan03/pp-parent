package com.project.service.user;

import com.project.dao.user.UserDao;
import com.project.entity.tables.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User getUserInfo(String account) {
        User user = userDao.selectByAccount(account);

        return user;
    }

    public List<User> pageList() {
        return null;
    }
}