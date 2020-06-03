package com.pp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/page")
public class PageController {

    @RequestMapping(value = "/{page}")
    public String page(@PathVariable(name = "page") String page) {
        return page;
    }

    @RequestMapping(value = "/{path}/{page}")
    public String page(@PathVariable(name = "path") String path, @PathVariable(name = "page") String page) {
        return path + "/" + page;
    }
}