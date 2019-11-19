package com.project.entity.tables.role;

import lombok.Data;

@Data
public class RoleResource {
    private Long id;
    private String uuid;
    private String roleUuid; // 角色uuid
    private String roleName; // 角色名称
    private String parentUrl; // 父路径
    private String resourceUrl; // 资源路径
    private String resourceName;// 资源名称
    private Boolean enable; // 0：停用(未选中) 1 启用(选中) // 不是数据库字段
    private String createTime;
}