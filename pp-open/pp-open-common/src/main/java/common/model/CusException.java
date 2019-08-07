package common.model;

import java.util.List;

import common.model.result.StatusEnum;

/**
 * 自定义异常
 * 
 * @author David
 *
 */
public class CusException extends RuntimeException {
	private static final long serialVersionUID = 8900977594896382046L;

	private StatusEnum statusEnum;
	private String msg;
	private Object data;
	private List<Object> dataList;

	public CusException(StatusEnum statusEnum) {
		this.statusEnum = statusEnum;
		this.msg = statusEnum.message;
	}

	public CusException(StatusEnum statusEnum, String msg) {
		this.statusEnum = statusEnum;
		this.msg = msg;
	}

	public StatusEnum getStatusEnum() {
		return statusEnum;
	}

	public String getMsg() {
		return msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<Object> getDataList() {
		return dataList;
	}

	public void setDataList(List<Object> dataList) {
		this.dataList = dataList;
	}

	public void setStatusEnum(StatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}