package com.project.entity.tables.resource;

import java.util.List;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Resource {
	@TableId
	private Long id;
	private String parentUrl;
	private String resourceName;
	private String resourceUrl;
	private String resourceMethod;
	private String resourceParam;
	private String remark;
	private String createTime;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<Resource> childrens;

	public Resource(String parentUrl, String resourceName, String resourceUrl) {
		this.parentUrl = parentUrl;
		this.resourceName = resourceName;
		this.resourceUrl = resourceUrl;
	}
}