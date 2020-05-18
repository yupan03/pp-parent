package com.pp.controller;

import com.pp.entity.tables.user.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @PostMapping(value = "/add")
    public Object add(User user) {
        return null;
    }

    @PostMapping(value = "/update")
    public Object update(User user) {
        return null;
    }

    @PostMapping(value = "/delete")
    public Object delete(User user) {
        return null;
    }

    @PostMapping(value = "/pageList")
    public Object pageList(User user) {
        return null;
    }
}