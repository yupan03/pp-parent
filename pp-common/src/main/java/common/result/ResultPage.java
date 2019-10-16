package common.result;

import java.util.List;

public class ResultPage<T> extends ResultList<T> {
    private Integer pageSize;
    private Integer pageNum;
    private Long total;

    public ResultPage(int status) {
        super(status);
    }

    public ResultPage(int status, String msg) {
        super(status, msg);
    }

    public ResultPage(int status, List<T> dataList, Integer pageSize, Integer pageNum, Long total) {
        super(status, dataList);
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.total = total;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}