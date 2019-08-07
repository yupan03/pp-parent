package com.project.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.entity.tables.user.UserRoleResource;

public interface UserRoleResourceDao {
	List<UserRoleResource> getUserRoleResourceByUser(@Param("userUuid") String userUuid);
}