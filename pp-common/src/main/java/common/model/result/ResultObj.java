package common.model.result;

import common.model.StatusEnum;

public class ResultObj<T> extends Result {
	private T data;

	public ResultObj(StatusEnum statusEnum) {
		super(statusEnum);
	}

	public ResultObj(StatusEnum statusEnum, String msg) {
		super(statusEnum, msg);
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}