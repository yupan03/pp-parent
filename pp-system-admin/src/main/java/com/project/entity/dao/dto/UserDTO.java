package com.project.entity.dao.dto;

import com.project.constant.status.UserState;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;

    private String account;

    private String name;

    private String password;

    private String email;

    private UserState state;

    private Integer type;

    private String createTime;
}
