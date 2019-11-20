package com.project.dao.resource;

import com.project.entity.tables.resource.Resource;

import java.util.List;

public interface ResourceDao {
    int insertList(List<Resource> resources);
}