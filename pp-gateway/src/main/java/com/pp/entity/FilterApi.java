package com.pp.entity;

/**
 * 网关不需要token验证的api
 *
 * @author David
 */
public class FilterApi {
    private Integer id;
    // 路由名称
    private String url;
    // 完全匹配、前缀、后缀
    private Integer type;
    // 描述
    private String remark;
    // 创建时间
    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}