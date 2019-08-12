package com.project.entity.tables.user;

import lombok.Data;

@Data
public class UserRoleResource {
    private Long id;

    private String uuid;
    private String userUuid;
    private String userName;
    private String parentUrl;
    private String resourceUrl;
    private String resourceName;
    private boolean enable; // 0：停用 1 启用
    private String createTime;
}