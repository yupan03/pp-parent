package com.pp.entity.tables;

import lombok.Data;

@Data
public class Number {
    private Integer id;

    private String uuid;
    private String createTime;
    private String type;
    private String number;
}