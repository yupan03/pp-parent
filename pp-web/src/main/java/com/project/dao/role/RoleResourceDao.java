package com.project.dao.role;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.entity.tables.role.RoleResource;

public interface RoleResourceDao {
	List<RoleResource> findByRole(@Param("roleUuid") String roleUuid, @Param("roleName") String roleName);

	int deleteByRole(@Param("roleUuid") String roleUuid, @Param("parentUrl") String parentUrl,
			@Param("resourceUrl") String resourceUrl);
}