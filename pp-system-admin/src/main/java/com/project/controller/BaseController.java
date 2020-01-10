package com.project.controller;

import com.github.pagehelper.PageInfo;
import common.result.Result;
import common.result.ResultList;
import common.result.ResultObj;
import common.result.ResultPage;

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
        return new ResultPage(status, msg, pageInfo.getList(), pageInfo.getSize(), pageInfo.getPageNum(),
                pageInfo.getTotal());
    }

    protected Result result(int status, String msg) {
        return new Result(status, msg);
    }

    protected <T> ResultObj<T> resultObj(int status, String msg, T t) {
        return new ResultObj(status, msg, t);
    }

    protected <T> ResultList<T> resultList(int status, String msg, List<T> list) {
        return new ResultList(status, msg, list);
    }
}