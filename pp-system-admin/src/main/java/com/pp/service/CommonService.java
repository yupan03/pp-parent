package com.pp.service;

import com.github.pagehelper.PageHelper;
import com.pp.dao.UserDao;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DAO管理类，所有的DAO都放在这里及一些公共方法
 */
@Service
@Getter
public final class CommonService {

    /**
     * PageHelper分页插件
     *
     * @param pageNum  当前页码
     * @param pageSize 分页大小
     * @param flag     是否分页，主要目的是为了列表页面和导出数据能否复用同一个service,
     *                 最后主装分页传递到页面在BaseController层进行封装
     */
    public void startPage(Integer pageNum, Integer pageSize, boolean flag) {
        if (flag) {
            PageHelper.startPage(pageNum, pageSize);
        }
    }

    /**
     * PageHelper分页插件
     *
     * @param pageNum  当前页码
     * @param pageSize 分页大小
     */
    public void startPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
    }

    @Autowired
    private UserDao userDao;
}