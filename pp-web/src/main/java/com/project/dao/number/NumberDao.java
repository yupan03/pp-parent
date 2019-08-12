package com.project.dao.number;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.entity.tables.Number;

public interface NumberDao extends BaseMapper<Number> {
    Number selectLastNumber(@Param("type") String type);
}