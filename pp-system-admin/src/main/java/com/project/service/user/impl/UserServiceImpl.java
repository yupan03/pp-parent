package com.project.service.user.impl;

import com.project.service.CommonService;
import com.project.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private CommonService commonService;

    public void test() {
        commonService.getUserDao();
    }
}