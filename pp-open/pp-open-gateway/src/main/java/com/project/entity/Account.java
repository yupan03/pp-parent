package com.project.entity;

import lombok.Data;

import java.util.List;

@Data
public class Account {
    // 账号
    private String username;
    // 密码
    private String password;
    // 角色
    private List<String> roles;
    // 资源
    private List<String> resources;
}