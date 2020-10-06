package com.pp.dao.user;

import com.pp.entity.tables.user.UserRoleResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleResourceDao {
    List<UserRoleResource> getUserRoleResourceByUser(@Param("userUuid") String userUuid);
}