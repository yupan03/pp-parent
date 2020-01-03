package com.project.entity.dao.qo;

import lombok.Data;

@Data
public class UserQO {
    private long id;

    private String account;

    private String name;

    private String password;

    private String email;

    private Integer state;

    private Integer type;

    private String createTime;
}