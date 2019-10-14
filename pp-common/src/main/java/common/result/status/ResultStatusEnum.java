package common.result.status;

/**
 * 错误业务状态码
 * 
 * @author David
 *
 */
public enum ResultStatusEnum {
    SUCCESS(200, "请求成功"),

    ERROR(500, "服务器错误"),

    ERROR_SQL(5001, "数据库错误"),

    ERROR_PARAM(5002, "参数错误"),

    WX_ERROR(5003, "数据库错误"),

    AUTH_FAIL(4001, "没有认证"),

    ACCESS_DENIED(403, "没有权限"),

    URL_NOT_FOUND(404, "请求不存在"),

    URL_METHOD_EOOR(405, "请求方式错误"),

    TOO_MANY_REQUESTS(429, "请求过多"),

    LOGIN_FAIL(5005, "登录失败"),

    LOGIN_EXPIRE(5006, "登录过期");

    public int status;
    public String message;

    ResultStatusEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }

}