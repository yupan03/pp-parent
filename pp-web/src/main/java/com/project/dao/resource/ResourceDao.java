package com.project.dao.resource;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.entity.tables.resource.Resource;

public interface ResourceDao extends BaseMapper<Resource> {
	int insertList(List<Resource> resources);
}