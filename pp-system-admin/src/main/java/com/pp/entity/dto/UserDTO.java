package com.pp.entity.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;

    private String account;

    private String name;

    private String password;

    private String email;

    private Byte state;

    private Byte type;

    private String createTime;
}