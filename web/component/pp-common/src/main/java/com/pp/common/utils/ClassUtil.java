package com.pp.common.utils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ClassUtil {
    /**
     * 获取当前类的所有实现子类
     *
     * @param superClass
     * @return
     * @throws ClassNotFoundException
     */
    public static List<Class<?>> getAllAssignedClass(Class<?> superClass) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        for (Class<?> c : getClasses(superClass)) {
            if (superClass.isAssignableFrom(c) && !superClass.equals(c)) {
                classes.add(c);
            }
        }
        return classes;
    }

    private static List<Class<?>> getClasses(Class<?> cls) throws ClassNotFoundException {
        String pk = cls.getPackage().getName();
        String path = pk.replace(".", "/");
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL url = classloader.getResource(path);
        return getClasses(new File(url.getFile()), pk);
    }

    private static List<Class<?>> getClasses(File dir, String pk) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!dir.exists()) {
            return classes;
        }
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                classes.addAll(getClasses(file, pk + "." + file.getName()));
            }
            String fileName = file.getName();
            if (fileName.endsWith(".class")) {
                classes.add(Class.forName(pk + "." + fileName.substring(0, fileName.length() - 6)));
            }
        }
        return classes;
    }
}