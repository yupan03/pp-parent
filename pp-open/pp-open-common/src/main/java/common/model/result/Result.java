package common.model.result;

public class Result {
	private int status;
	private String msg;

	public Result(StatusEnum statusEnum) {
		this.status = statusEnum.status;
		this.msg = statusEnum.message;
	}

	public Result(StatusEnum statusEnum, String msg) {
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