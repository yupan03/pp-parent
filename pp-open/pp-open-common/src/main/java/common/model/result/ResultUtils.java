package common.model.result;

import java.util.List;

/**
 * 前端页面返回值封装（错误信息返回统一抛出自定义异常）
 * 
 * @author David
 *
 */
public class ResultUtils {
    public static Result success() {
        return new Result(StatusEnum.SUCCESS);
    }

    public static <T> ResultObj<T> success(T object) {
        ResultObj<T> result = new ResultObj<>(StatusEnum.SUCCESS);
        result.setData(object);
        return result;
    }

    public static <T> ResultList<T> success(List<T> dataList) {
        ResultList<T> result = new ResultList<>(StatusEnum.SUCCESS);
        result.setDataList(dataList);
        return result;
    }

    public static <T> ResultPage<T> success(Long pageNum, Long pageSize, Long total, List<T> dataList) {
        ResultPage<T> result = new ResultPage<>(StatusEnum.SUCCESS);
        result.setDataList(dataList);
        result.setPageNum(pageNum);
        result.setPageSize(pageSize);
        result.setTotal(total);

        return result;
    }
}