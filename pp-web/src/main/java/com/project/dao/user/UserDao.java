package com.project.dao.user;

import com.project.entity.tables.user.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    User selectByAccount(@Param("account") String account);
}