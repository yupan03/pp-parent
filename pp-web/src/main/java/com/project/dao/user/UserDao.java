package com.project.dao.user;

import org.apache.ibatis.annotations.Param;
import com.project.entity.tables.user.User;

public interface UserDao {
    User selectByAccount(@Param("account") String account);
}