package common.result;

import common.result.status.ResultStatusEnum;

public class Result {
    private int status;
    private String msg;

    public Result(ResultStatusEnum statusEnum) {
        this.status = statusEnum.status;
        this.msg = statusEnum.message;
    }

    public Result(ResultStatusEnum statusEnum, String msg) {
        this.status = statusEnum.status;
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

    public void setMsg(String msg) {
        this.msg = msg;
    }
}