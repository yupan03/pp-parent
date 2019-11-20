package com.project.service;

import com.project.dao.UserDao;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DAO管理类，所有的DAO都放在这里及一些公共方法
 */
@Service
@Getter
public class CommonService {
    @Autowired
    private UserDao userDao;
}