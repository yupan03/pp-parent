package com.pp.dao.role;

import com.pp.entity.tables.role.RoleResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleResourceDao {
    List<RoleResource> findByRole(@Param("roleUuid") String roleUuid, @Param("roleName") String roleName);

    int deleteByRole(@Param("roleUuid") String roleUuid, @Param("parentUrl") String parentUrl,
                     @Param("resourceUrl") String resourceUrl);
}