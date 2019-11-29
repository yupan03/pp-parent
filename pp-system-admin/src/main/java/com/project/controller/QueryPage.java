package com.project.controller;

import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiModelProperty;

public class QueryPage {
    // 分页大小
    @ApiModelProperty(value = "分页大小", required = true, position = 1)
    private Integer pageSize = 15;
    // 分页当前页码
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

    /**
     * 手动设置是否开始分页（Excel导出和列表能够使用同一个service,增加代码的复用性）
     *
     * @param flag
     */
    @ApiModelProperty(hidden = true)
    public void startPage(boolean flag) {
        if (flag)
            PageHelper.startPage(pageNum, pageSize);
    }
}