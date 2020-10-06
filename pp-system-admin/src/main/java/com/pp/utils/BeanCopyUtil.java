package com.pp.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtil {

    public static <E, T> T copyObject(E source, Class<T> targetClass) {
        try {
            T t = targetClass.newInstance();
            BeanUtils.copyProperties(source, t);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <E,T> List<T> copyList(List<E> sources, Class<T> targetClass) {
        if (sources == null) {
            return null;
        }
        return sources.stream().map(source ->copyObject(source, targetClass)).collect(Collectors.toList());
    }
}