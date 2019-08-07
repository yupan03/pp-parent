package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.tables.resource.Resource;
import com.project.service.resource.ResourceService;

import common.model.result.ResultList;
import common.model.result.ResultUtils;

@RestController
@RequestMapping(value = "/resource")
public class ResourceController {
	@Autowired
	private ResourceService resourceService;

	@PostMapping(value = "/pageList")
	public ResultList<Resource> pageList() {
		return ResultUtils.success(resourceService.pageList());
	}
}