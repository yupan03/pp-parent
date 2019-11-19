package com.project.dao.number;

import org.apache.ibatis.annotations.Param;

import com.project.entity.tables.Number;

public interface NumberDao {
    Number selectLastNumber(@Param("type") String type);
}