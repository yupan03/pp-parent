package com.project.service.user.impl;

import com.project.service.AbstractService;
import com.project.service.user.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author David
 * @since 2019-08-02
 */
@Service
public class UserServiceImpl extends AbstractService implements UserService {
    public void test() {
        super.commonService.getUserDao();
    }
}