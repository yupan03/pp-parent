package com.project.service.user.impl;

import com.project.service.AbstractService;
import com.project.service.user.UserService;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
public class UserServiceImpl extends AbstractService implements UserService {
    public void test() {
        super.commonService.getUserDao();
    }
}