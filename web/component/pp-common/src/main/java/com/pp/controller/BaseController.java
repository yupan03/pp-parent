package com.pp.controller;

import com.github.pagehelper.PageInfo;
import com.pp.common.result.Result;
import com.pp.common.result.ResultList;
import com.pp.common.result.ResultObj;
import com.pp.common.result.ResultPage;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * controller层公共类
 *
 * @author David
 */
public class BaseController {

    /**
     * 默认返回提示语为success
     */
    protected String msg = "success";

    protected final <T> ResultPage<T> resultPage(List<T> list) {
        PageInfo<T> pageInfo = new PageInfo<>(list);
        return new ResultPage<>(HttpStatus.OK.value(), msg, list, pageInfo.getPageSize(), pageInfo.getPageNum(), pageInfo.getTotal());
    }

    protected final <T, E> ResultPage<E> resultPage(List<T> list, Class<E> targetClass) {
        return this.resultPage(HttpStatus.OK.value(), msg, list, targetClass);
    }

    protected final <T, E> ResultPage<E> resultPage(int status, String msg, List<T> list, Class<E> targetClass) {
        PageInfo<T> pageInfo = new PageInfo<>(list);
        List<E> ts = copyList(list, targetClass);
        return new ResultPage<>(status, msg, ts, pageInfo.getPageSize(), pageInfo.getPageNum(), pageInfo.getTotal());
    }

    protected final Result result() {
        return this.result(HttpStatus.OK.value(), msg);
    }

    protected final Result result(int status, String msg) {
        return new Result(status, msg);
    }

    protected final <T> ResultObj<T> resultObj(T t) {
        return new ResultObj<>(HttpStatus.OK.value(), msg, t);
    }

    protected final <T, E> ResultObj<E> resultObj(T t, Class<E> targetClass) {
        return this.resultObj(HttpStatus.OK.value(), msg, t, targetClass);
    }

    protected final <T, E> ResultObj<E> resultObj(int status, String msg, T t, Class<E> targetClass) {
        E e = null;
        try {
            e = targetClass.newInstance();
            this.copy(t, e);
        } catch (Exception illegalAccessException) {
            illegalAccessException.printStackTrace();
        }

        return new ResultObj<>(status, msg, e);
    }

    protected final <T> ResultList<T> resultList(List<T> list) {
        return new ResultList<>(HttpStatus.OK.value(), msg, list);
    }

    protected final <T, E> ResultList<E> resultList(List<T> list, Class<E> targetClass) {
        return this.resultList(HttpStatus.OK.value(), msg, list, targetClass);
    }

    protected final <T, E> ResultList<E> resultList(int status, String msg, List<T> list, Class<E> targetClass) {
        List<E> ts = copyList(list, targetClass);
        return new ResultList<>(status, msg, ts);
    }

    protected final void copy(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }

    protected final <T, E> List<T> copyList(List<E> source, Class<T> targetClass) {
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