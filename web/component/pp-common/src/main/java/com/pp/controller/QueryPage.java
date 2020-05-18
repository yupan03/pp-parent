package com.pp.controller;

/**
 * 分页
 */
public class QueryPage {
    /**
     * 分页大小
     */
    private Integer pageSize = 15;
    /**
     * 当前页码
     */
    private Integer pageNum = 1;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}