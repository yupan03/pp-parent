package com.project.controller;

import io.swagger.annotations.ApiModelProperty;

public class QueryPage {
    @ApiModelProperty(value = "分页大小", required = true, position = 1)
    private Integer pageSize = 15;
    @ApiModelProperty(value = "分页当前页码", required = true, position = 2)
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