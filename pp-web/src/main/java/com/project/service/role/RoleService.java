package com.project.service.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.role.RoleDao;
import com.project.dao.role.RoleResourceDao;
import com.project.entity.tables.role.Role;
import com.project.entity.tables.role.RoleResource;

import common.result.exception.ResultException;
import common.result.status.ResultStatusEnum;
import common.utils.SysUtils;

@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoleResourceDao resourceDao;

    public Object add(Role role) {
        role.setCreateTime(SysUtils.getCurrentTime());
        roleDao.insert(role);
        // TODO 判断这个角色是否已经存在
        return role.getId();
    }

    public Object update(Role role) {
        return roleDao.updateById(role);
    }

    public Object delete(String id) {

        return roleDao.deleteById(id);
    }

    public Object pageList() {
        return null;
    }

    public List<RoleResource> roleResourceList(String roleId) {
        // 先判断角色是否存在
        Role role = roleDao.selectById(roleId);
        if (role == null) {
            throw new ResultException(ResultStatusEnum.ERROR, "角色不存在");
        }

        List<RoleResource> resources = resourceDao.findByRole(role.getId(), role.getName());

        return resources;
    }
}