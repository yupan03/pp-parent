package com.pp.common.utils;

public class IdUtil {
    /**
     * 获取雪花算法ID
     */
    public static Long getId() {
        return Snowflake.getInstance().nextId();
    }
}