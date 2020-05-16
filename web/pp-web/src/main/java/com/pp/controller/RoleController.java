package com.pp.controller;

import com.pp.entity.tables.role.Role;
import com.pp.entity.tables.role.RoleResource;
import com.pp.exception.BusinessException;
import com.pp.exception.BusinessStatus;
import com.pp.service.role.RoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "角色管理")
@RestController
@RequestMapping(value = "/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping(value = "/add")
    public Object add(Role role) {
        return roleService.add(role);
    }

    @PostMapping(value = "/update")
    public Object update(Role role) {
        return roleService.update(role);
    }

    @PostMapping(value = "/delete")
    public Object delete(String id) {
        return roleService.delete(id);
    }

    @PostMapping(value = "/pageList")
    public Object pageList() {
        return null;
    }

    @PostMapping(value = "/roleResourceList")
    public Object roleResourceList(String roleId) {
        if (StringUtils.isEmpty(roleId)) {
            throw new BusinessException(BusinessStatus.ERROR, "参数不能为空");
        }

        return roleService.roleResourceList(roleId);
    }

    @PostMapping(value = "/updateRoleResource")
    public Object updateRoleResource(@RequestBody List<RoleResource> roleResources) {
        return null;
    }
}