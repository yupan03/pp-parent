package common.result;

import java.util.List;

public class ResultList<T> extends Result {
    private List<T> dataList;

    public ResultList(ResultStatusEnum statusEnum) {
        super(statusEnum);
    }

    public ResultList(ResultStatusEnum statusEnum, String msg) {
        super(statusEnum, msg);
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}