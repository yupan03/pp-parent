package com.project.exception;

import java.util.List;

/**
 * 自定义业务异常
 * 
 * @author David
 *
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 8900977594896382046L;

    private BusinessStatus statusEnum;
    private String msg;
    private Object data;
    private List<Object> dataList;

    public BusinessException(BusinessStatus statusEnum) {
        this.statusEnum = statusEnum;
        this.msg = statusEnum.message;
    }

    public BusinessException(BusinessStatus statusEnum, String msg) {
        this.statusEnum = statusEnum;
        this.msg = msg;
    }

    public BusinessStatus getStatusEnum() {
        return statusEnum;
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

    public void setStatusEnum(BusinessStatus statusEnum) {
        this.statusEnum = statusEnum;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}