package com.pp.entity;

/**
 * 网关不需要token验证的api
 * 
 * @author David
 */
public class FilterApi {
    private Integer id;
    private String url;
    private Integer type;// 完全匹配、前缀、后缀
    private String remark; // 描述
    private String createTime;// 创建时间

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