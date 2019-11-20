package com.project.service.resource;

import com.project.dao.resource.ResourceDao;
import com.project.entity.tables.resource.Resource;
import com.project.utils.ResourceTreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {
    @Autowired
    private ResourceDao resourceDao;

    public List<Resource> pageList() {
        // 取出所有资源数据然后封装成树的形式
        List<Resource> resources = ResourceTreeUtil.buildByRecursive(null);

        return resources;
    }
}