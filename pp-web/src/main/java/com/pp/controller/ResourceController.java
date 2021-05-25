package com.pp.controller;

import com.pp.entity.tables.resource.Resource;
import com.pp.result.Page;
import com.pp.result.Result;
import com.pp.service.resource.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/resource")
public class ResourceController extends BaseController {
    @Autowired
    private ResourceService resourceService;

    @PostMapping(value = "/pageList")
    public Result<Page<Resource>> pageList() {
        return super.resultPage(resourceService.pageList());
    }
}