package common.result;

import java.util.List;

public class ResultList<T> extends Result {
    private List<T> dataList;

    public ResultList(int status) {
        super(status);
    }

    public ResultList(int status, String msg) {
        super(status, msg);
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}