package com.project.dao.user;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.entity.tables.user.User;

public interface UserDao extends BaseMapper<User> {
	User selectByAccount(@Param("account") String account);
}