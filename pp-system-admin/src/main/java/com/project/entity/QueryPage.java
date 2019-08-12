package com.project.entity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import common.result.ResultPage;
import common.result.ResultUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QueryPage<T> {
    // 分页大小
    @ApiModelProperty(value = "分页大小", required = true, position = 1)
    private Long pageSize = 15L;
    // 分页当前页码
    @ApiModelProperty(value = "分页当前页码", required = true, position = 2)
    private Long pageNum = 1L;

    @ApiModelProperty(hidden = true)
    private Page<T> page = null;

    public Page<T> getPage() {
        page = new Page<T>(pageNum, pageSize);
        return page;
    }

    @ApiModelProperty(hidden = true)
    public ResultPage<T> getResultPage() {
        return ResultUtils.success(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords());
    }
}