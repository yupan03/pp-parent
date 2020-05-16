package com.pp.constant;

public enum DeleteEnum {
    DELETE(0, "停用"),

    NORMAL(1, "正常");

    public final Integer value;
    public final String desc;

    DeleteEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}