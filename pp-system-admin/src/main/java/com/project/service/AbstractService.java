package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 公用属性和方法
 */
public abstract class AbstractService {
    @Autowired
    protected CommonService commonService;
}