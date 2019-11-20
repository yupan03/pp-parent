package com.project.controller;

import com.github.pagehelper.PageInfo;
import common.result.ResultPage;

import java.util.List;

/**
 * controller层公共类
 * 
 * @author David
 */
public class CommonController {
    /**
     * PageHelper数据封装
     * 
     * @param <T> 实体对象
     * @param status 返回状态
     * @param list 列表数据
     * @return 返回分页数据
     */
    protected <T> ResultPage<T> pageList(int status, List<T> list) {
        PageInfo<T> pageInfo = new PageInfo<>(list);
        return new ResultPage<>(status, pageInfo.getList(), pageInfo.getSize(), pageInfo.getPageNum(),
                pageInfo.getTotal());
    }
}