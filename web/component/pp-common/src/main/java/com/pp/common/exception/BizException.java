package com.pp.common.exception;

import java.util.List;

/**
 * 自定义业务异常
 *
 * @author David
 */
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 8900977594896382046L;

    private int status;
    private String msg;
    private Object data;
    private List<Object> dataList;

    public BizException(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<Object> getDataList() {
        return dataList;
    }

    public void setDataList(List<Object> dataList) {
        this.dataList = dataList;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}