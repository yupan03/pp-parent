package com.pp.common.result;

public class ResultObj<T> extends Result {
    private T data;

    public ResultObj(){
        super();
    }

    public ResultObj(int status, String msg, T data) {
        super(status, msg);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}