package common.result;

import common.result.status.ResultStatusEnum;

public class ResultObj<T> extends Result {
    private T data;

    public ResultObj(ResultStatusEnum statusEnum) {
        super(statusEnum);
    }

    public ResultObj(ResultStatusEnum statusEnum, String msg) {
        super(statusEnum, msg);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}