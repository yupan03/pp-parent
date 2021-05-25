package com.pp.controller;

import com.github.pagehelper.PageInfo;
import com.pp.exception.BizException;
import com.pp.jwt.JwtUtil;
import com.pp.jwt.LoginAccount;
import com.pp.result.Page;
import com.pp.result.Result;
import com.pp.utils.BeanCopyUtil;
import com.pp.utils.WebUtil;
import org.springframework.http.HttpStatus;

import javax.annotation.Resource;
import java.util.List;

/**
 * controller层公共类
 *
 * @author David
 */
public class BaseController {
    /**
     * 默认返回成功状态(200)
     */
    protected int status = HttpStatus.OK.value();

    /**
     * 默认返回提示语为success
     */
    protected String msg = "success";
    @Resource
    private JwtUtil jwtUtil;

    /**
     * 获取当前用户ID
     */
    protected final Long curUserId() {
        String token = WebUtil.getToken();
        LoginAccount account = jwtUtil.getAccountFromToken(token);
        if (account == null) {
            throw new BizException(401, "非法请求");
        }
        return account.getId();
    }

    protected final Result<Void> result(int status, String msg) {
        return new Result<>(status, msg);
    }

    public final <T> Result<T> result(int status, String msg, T t) {
        return new Result<>(status, msg, t);
    }

    protected final Result<Void> result() {
        return this.result(status, msg);
    }

    protected final <T> Result<T> result(T t) {
        return this.result(status, msg, t);
    }

    protected final <T> Result<List<T>> result(List<T> list) {
        return this.result(status, msg, list);
    }

    protected final <T, E> Result<E> result(T t, Class<E> targetClass) {
        return this.result(status, msg, BeanCopyUtil.copyObject(t, targetClass));
    }

    protected final <T, E> Result<List<E>> result(List<T> list, Class<E> targetClass) {
        return this.result(status, msg, BeanCopyUtil.copyList(list, targetClass));
    }

    /**
     * 分页数据
     */
    protected final <T> Result<Page<T>> resultPage(List<T> list) {
        PageInfo<T> pageInfo = new PageInfo<>(list);

        Page<T> page = new Page<>();

        page.setPageList(list);
        page.setPageNum(pageInfo.getPageNum());
        page.setPageSize(pageInfo.getPageSize());
        page.setTotal(pageInfo.getTotal());

        return this.result(page);
    }

    protected final <T, E> Result<Page<E>> resultPage(List<T> list, Class<E> targetClass) {
        PageInfo<T> pageInfo = new PageInfo<>(list);

        Page<E> page = new Page<>();

        page.setPageList(BeanCopyUtil.copyList(list, targetClass));
        page.setPageNum(pageInfo.getPageNum());
        page.setPageSize(pageInfo.getPageSize());
        page.setTotal(pageInfo.getTotal());

        return this.result(page);
    }
}