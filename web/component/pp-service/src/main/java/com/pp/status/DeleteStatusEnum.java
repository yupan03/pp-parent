package com.pp.status;

/**
 * 删除状态
 */
public enum DeleteStatusEnum {
    DELETE((byte) 0, "停用"),

    NORMAL((byte) 1, "正常");

    private final byte value;
    private final String desc;

    DeleteStatusEnum(byte value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}