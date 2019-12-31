package common.result;

import java.util.List;

public class ResultList<T> extends Result {
    private List<T> dataList;

    public ResultList(int status, List<T> dataList) {
        super(status);
        this.dataList = dataList;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}