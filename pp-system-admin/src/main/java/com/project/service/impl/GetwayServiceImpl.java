package com.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.constant.BusinessStatus;
import com.project.dao.GatewayRouteDefinitionDao;
import com.project.entity.QueryPage;
import com.project.entity.table.GatewayRouteDefinition;
import com.project.feign.GatewayFeign;

import common.exception.BusinessException;
import common.utils.SysUtils;

@Service
public class GetwayServiceImpl extends ServiceImpl<GatewayRouteDefinitionDao, GatewayRouteDefinition> {
    @Autowired
    private GatewayRouteDefinitionDao gatewayRouteDefinitionDao;
    @Autowired
    private GatewayFeign gatewayFeign;

    @Transactional
    public int add(GatewayRouteDefinition geRouteDefinition) {
        geRouteDefinition.setCreateTime(SysUtils.getCurrentTime());
        gatewayRouteDefinitionDao.insert(geRouteDefinition);

//		boolean flage = gatewayFeign.add(routeDefinition);
//		if (!flage) {
//			throw new ProjectException(StatusEnum.ERROR, "新增路由失败");
//		}

        return 1;
    }

    @Transactional
    public int update(GatewayRouteDefinition routeDefinition) {
        gatewayRouteDefinitionDao.updateById(routeDefinition);
        boolean flage = gatewayFeign.update(routeDefinition);
        if (!flage) {
            throw new BusinessException(BusinessStatus.ERROR.status, "更新路由失败");
        }

        return 1;
    }

    @Transactional
    public int delete(Integer id) {
        GatewayRouteDefinition routeDefinition = gatewayRouteDefinitionDao.selectById(id);

        gatewayRouteDefinitionDao.deleteById(routeDefinition.getId());

        boolean flage = gatewayFeign.delete(routeDefinition.getName());
        if (!flage) {
            throw new BusinessException(BusinessStatus.ERROR.status, "删除路由失败");
        }

        return 1;
    }

    public Page<GatewayRouteDefinition> getAll(QueryPage query) {
        query.startPage(true);
        gatewayRouteDefinitionDao.selectList(null);
        return null;
    }
}