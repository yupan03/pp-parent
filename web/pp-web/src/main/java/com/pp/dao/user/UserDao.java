package com.pp.dao.user;

import com.pp.entity.tables.user.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    User selectByAccount(@Param("account") String account);
}