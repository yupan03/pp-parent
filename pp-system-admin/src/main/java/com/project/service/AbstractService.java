package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * service层公用属性和方法，禁止直接注入父类，否则会报错（expected single matching bean but found 2）
 */
public abstract class AbstractService {
    @Autowired
    protected CommonService commonService;
}