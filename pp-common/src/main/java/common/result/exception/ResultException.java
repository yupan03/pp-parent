package common.result.exception;

import java.util.List;

import common.result.status.ResultStatusEnum;

/**
 * 自定义异常
 * 
 * @author David
 *
 */
public class ResultException extends RuntimeException {
    private static final long serialVersionUID = 8900977594896382046L;

    private ResultStatusEnum statusEnum;
    private String msg;
    private Object data;
    private List<Object> dataList;

    public ResultException(ResultStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
        this.msg = statusEnum.message;
    }

    public ResultException(ResultStatusEnum statusEnum, String msg) {
        this.statusEnum = statusEnum;
        this.msg = msg;
    }

    public ResultStatusEnum getStatusEnum() {
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

    public void setStatusEnum(ResultStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}