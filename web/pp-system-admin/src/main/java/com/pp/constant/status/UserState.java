package com.pp.constant.status;

/**
 * 用户状态
 */
public enum UserState {
    NO(0, "停用"),

    YES(1, "正常");

    private final Integer value;
    private final String desc;

    UserState(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}