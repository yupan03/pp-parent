package com.pp.dao.resource;

import com.pp.entity.tables.resource.Resource;

import java.util.List;

public interface ResourceDao {
    int insertList(List<Resource> resources);
}