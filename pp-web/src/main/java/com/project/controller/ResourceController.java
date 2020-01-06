package com.project.controller;

import com.project.entity.tables.resource.Resource;
import com.project.service.resource.ResourceService;
import common.result.ResultList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @PostMapping(value = "/pageList")
    public ResultList<Resource> pageList() {
        return new ResultList<>(HttpStatus.OK.value(),"", resourceService.pageList());
    }
}