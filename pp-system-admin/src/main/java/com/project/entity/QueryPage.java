package com.project.entity;

import org.springframework.http.HttpStatus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import common.result.ResultPage;
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
        return new ResultPage<T>(HttpStatus.OK.value(), page.getRecords(), page.getSize(), page.getCurrent(),
                page.getTotal());
    }
}