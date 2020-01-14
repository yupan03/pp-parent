package com.project.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class JSONUtil {

    public static String objectToJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            // 增加日志记录
        }

        return null;
    }

    public static <T> T jsonToObject(String json, Class<T> tClass) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(json, tClass);
        } catch (IOException e) {
            e.printStackTrace();
            // 增加日志记录
        }

        return null;
    }

    public static <T> List<T> jsonToList(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, new TypeReference<List<T>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            // 增加日志记录
        }

        return null;
    }
}