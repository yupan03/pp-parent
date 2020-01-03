package com.project.utils;

import org.springframework.cglib.beans.BeanCopier;

public class BeanCopyUtil {

    public static <T> T copyObject(Class source, Object object, Class<T> target) {
        T t = null;
        try {
            t = target.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        BeanCopier beanCopier = BeanCopier.create(source, target, false);
        beanCopier.copy(object, t, null);
        return t;
    }
}
