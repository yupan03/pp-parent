package com.pp.service.user.impl;

import com.pp.controller.user.param.UserAdd;
import com.pp.service.CommonService;
import com.pp.service.user.UserService;
import com.pp.entity.dao.qo.UserQO;
import common.utils.IdUtil;
import org.springframework.beans.BeanUtils;
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
    public void addUser(UserAdd userAdd) {
        UserQO userQO = new UserQO();
        BeanUtils.copyProperties(userAdd, userQO);
        userQO.setId(IdUtil.getId());
        commonService.getUserDao().insertUser(userQO);
    }
}