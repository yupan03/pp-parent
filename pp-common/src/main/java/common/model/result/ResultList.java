package common.model.result;

import java.util.List;

import common.model.StatusEnum;

public class ResultList<T> extends Result {
	private List<T> dataList;

	public ResultList(StatusEnum statusEnum) {
		super(statusEnum);
	}

	public ResultList(StatusEnum statusEnum, String msg) {
		super(statusEnum, msg);
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
}