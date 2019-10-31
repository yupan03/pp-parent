package com.project.controller;

import java.util.List;

import com.github.pagehelper.PageInfo;

import common.result.ResultPage;

/**
 * controller层公共类
 * 
 * @author David
 */
public class CommonController {
    /**
     * PageHelper数据封装
     * 
     * @param <T>
     * @param status
     * @param list
     * @return
     */
    protected <T> ResultPage<T> pageList(int status, List<T> list) {
        PageInfo<T> pageInfo = new PageInfo<T>(list);
        return new ResultPage<T>(status, pageInfo.getList(), pageInfo.getSize(), pageInfo.getPageNum(),
                pageInfo.getTotal());
    }
}