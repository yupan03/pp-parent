package com.pp.entity.tables.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Resource {
    private Long id;
    private String parentUrl;
    private String resourceName;
    private String resourceUrl;
    private String resourceMethod;
    private String resourceParam;
    private String remark;
    private String createTime;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Resource> children;

    public Resource(String parentUrl, String resourceName, String resourceUrl) {
        this.parentUrl = parentUrl;
        this.resourceName = resourceName;
        this.resourceUrl = resourceUrl;
    }
}