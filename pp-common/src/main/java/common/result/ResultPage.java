package common.result;

import java.util.List;

public class ResultPage<T> extends ResultList<T> {
    private Long pageSize;
    private Long pageNum;
    private Long total;

    public ResultPage(int status) {
        super(status);
    }

    public ResultPage(int status, String msg) {
        super(status, msg);
    }

    public ResultPage(int status, List<T> dataList, Long pageSize, Long pageNum, Long total) {
        super(status, dataList);
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.total = total;
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