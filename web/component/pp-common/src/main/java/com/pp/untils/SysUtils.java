package com.pp.untils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class SysUtils {
    // 获取uuid
    public synchronized static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    /**
     * 获取当前时间
     *
     * @return 字符串时间
     */
    public static String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    /**
     * 获取当前时间
     *
     * @param pattern 日期格式
     * @return 字符串时间
     */
    public static String getCurrentTime(String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(new Date());
    }
}