package com.project.constant.status;

import com.fasterxml.jackson.annotation.JsonFormat;
import common.status.BaseEnum;
import lombok.Getter;

/**
 * 用户状态
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UserState implements BaseEnum<Integer> {
    NO(0, "停用"),

    YES(1, "正常");

    private final Integer value;
    @Getter
    private final String desc;

    UserState(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}