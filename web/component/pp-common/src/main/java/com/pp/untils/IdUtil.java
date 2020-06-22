package com.pp.untils;

public class IdUtil {
    /**
     * 获取雪花算法ID
     */
    public static Long getId() {
        return Snowflake.getInstance().nextId();
    }
}