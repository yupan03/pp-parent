package com.pp.utils;

import com.alibaba.fastjson.JSONObject;

public class JSONUtil {

    public static String objectToJson(Object object) {
        return JSONObject.toJSONString(object);

    }

    public static <T> T jsonToObject(String json, Class<T> clazz) {
        return JSONObject.parseObject(json, clazz);
    }
}