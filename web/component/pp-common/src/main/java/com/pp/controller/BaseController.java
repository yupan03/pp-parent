package com.pp.controller;

import com.github.pagehelper.PageInfo;
import com.pp.common.result.Result;
import com.pp.common.result.ResultList;
import com.pp.common.result.ResultObj;
import com.pp.common.result.ResultPage;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * controller层公共类
 *
 * @author David
 */
public class BaseController {
    /**
     * PageHelper数据封装
     *
     * @param <T>    实体对象
     * @param status 返回状态
     * @param list   列表数据
     * @return 返回分页数据
     */
    protected <T> ResultPage<T> resultPage(int status, String msg, List<T> list) {
        PageInfo<T> pageInfo = new PageInfo<>(list);
        return new ResultPage<>(status, msg, pageInfo.getList(), pageInfo.getSize(), pageInfo.getPageNum(),
                pageInfo.getTotal());
    }

    protected <T, E> ResultPage<E> resultPage(int status, String msg, List<T> list, Class<E> targetClass) {
        PageInfo<T> pageInfo = new PageInfo<>(list);
        List<E> ts = copyList(list, targetClass);
        return new ResultPage<>(status, msg, ts, pageInfo.getPageSize(), pageInfo.getPageNum(),
                pageInfo.getTotal());
    }

    protected Result result(int status, String msg) {
        return new Result(status, msg);
    }

    protected <T> ResultObj<T> resultObj(int status, String msg, T t) {
        return new ResultObj<>(status, msg, t);
    }

    protected <T, E> ResultObj<E> resultObj(int status, String msg, T t, Class<E> targetClass) {
        E e = null;
        try {
            e = targetClass.newInstance();
            this.copy(t, e);
        } catch (Exception illegalAccessException) {
            illegalAccessException.printStackTrace();
        }

        return new ResultObj<>(status, msg, e);
    }

    protected <T> ResultList<T> resultList(int status, String msg, List<T> list) {
        return new ResultList<>(status, msg, list);
    }

    protected <T, E> ResultList<E> resultList(int status, String msg, List<T> list, Class<E> targetClass) {
        List<E> ts = copyList(list, targetClass);
        return new ResultList<>(status, msg, ts);
    }

    protected void copy(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }

    protected <T, E> List<T> copyList(List<E> source, Class<T> targetClass) {
        List<T> target = new ArrayList<>();
        if (source != null && !source.isEmpty()) {
            source.forEach(obj -> {
                try {
                    T data = targetClass.newInstance();
                    BeanUtils.copyProperties(obj, data);
                    target.add(data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        return target;
    }
}