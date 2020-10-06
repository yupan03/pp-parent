package com.pp.result;

import java.util.List;

public class ResultList<T> extends Result {
    private List<T> dataList;

    public ResultList(int status, String msg, List<T> dataList) {
        super(status, msg);
        this.dataList = dataList;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}