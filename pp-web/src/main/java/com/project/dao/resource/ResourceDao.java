package com.project.dao.resource;

import java.util.List;

import com.project.entity.tables.resource.Resource;

public interface ResourceDao {
    int insertList(List<Resource> resources);
}