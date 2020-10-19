package com.pp.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 分页
 */
@Getter
@Setter
@NoArgsConstructor
public class QueryPage {
    /**
     * 分页大小
     */
    private Integer pageSize = 15;
    /**
     * 当前页码
     */
    private Integer pageNum = 1;
}