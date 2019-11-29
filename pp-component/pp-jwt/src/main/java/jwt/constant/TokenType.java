package jwt.constant;

public enum TokenType {

    NORMAL(0, "正常"),

    WILL_EXPIRE(1, "将过期"),

    OVERDUE(2, "已过期");

    private Integer type;
    private String desc;

    private TokenType(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}