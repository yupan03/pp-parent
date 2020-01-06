package com.project.config;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DozerCopyUtil {
    @Autowired
    private Mapper mapper;

    public <E, T> T copyObject(E source, Class<T> targetClass) {
        return mapper.map(source, targetClass);
    }


    public <E,T> List<T> copyList(List<E> sources, Class<T> targetClass) {
        if (sources == null) {
            return null;
        }
        return sources.stream().map(source -> mapper.map(source, targetClass)).collect(Collectors.toList());
    }
}