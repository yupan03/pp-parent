package common.result;

public class ResultObj<T> extends Result {
    private T data;

    public ResultObj(int status) {
        super(status);
    }

    public ResultObj(int status, String msg) {
        super(status, msg);
    }

    public ResultObj(int status, T data) {
        super(status);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}