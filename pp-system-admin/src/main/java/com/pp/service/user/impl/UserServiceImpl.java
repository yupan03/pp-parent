package com.pp.service.user.impl;

import com.pp.entity.table.User;
import com.pp.service.CommonService;
import com.pp.service.user.UserService;
import com.pp.untils.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private CommonService commonService;

    @Override
    public int addUser(User userAdd) {
        userAdd.setId(IdUtil.getId());
        return commonService.getUserDao().insertUser(userAdd);
    }
}