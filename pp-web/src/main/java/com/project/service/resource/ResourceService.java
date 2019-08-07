package com.project.service.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.resource.ResourceDao;
import com.project.entity.tables.resource.Resource;
import com.project.utils.ResourceTreeUtil;

@Service
public class ResourceService {
	@Autowired
	private ResourceDao resourceDao;

	public List<Resource> pageList() {
		// 取出所有资源数据然后封装成树的形式
		List<Resource> resources = ResourceTreeUtil.buildByRecursive(resourceDao.selectList(null));

		return resources;
	}
}