package com.pp.dao;

import com.pp.constant.status.UserState;
import com.pp.entity.dao.dto.UserDTO;
import com.pp.entity.dao.qo.UserQO;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface UserDao {

    int insertUser(UserQO userQO);

    /**
     * 更新用户状态
     *
     * @param id    主键
     * @param state 状态
     */
    int updateState(@Param("id") Long id, @Param("state") UserState state);

    int updateUser(UserQO userQO);



    List<UserDTO> getAllUser(UserQO userQO);
}