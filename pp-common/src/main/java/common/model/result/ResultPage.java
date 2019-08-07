package common.model.result;

import common.model.StatusEnum;

public class ResultPage<T> extends ResultList<T> {
	private Long pageSize;
	private Long pageNum;
	private Long total;

	public ResultPage(StatusEnum statusEnum) {
		super(statusEnum);
	}

	public ResultPage(StatusEnum statusEnum, String msg) {
		super(statusEnum, msg);
	}

	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public Long getPageNum() {
		return pageNum;
	}

	public void setPageNum(Long pageNum) {
		this.pageNum = pageNum;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}