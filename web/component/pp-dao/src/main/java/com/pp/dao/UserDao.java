package com.pp.dao;


import com.pp.entity.dto.UserDTO;
import com.pp.entity.table.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    int insertUser(User user);

    /**
     * 更新用户状态
     *
     * @param id    主键
     * @param state 状态
     */
    int updateState(@Param("id") Long id, @Param("state") Byte state);

    int updateUser(User user);


    List<UserDTO> getAllUser();
}