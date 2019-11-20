package com.project.dao.number;

import com.project.entity.tables.Number;
import org.apache.ibatis.annotations.Param;

public interface NumberDao {
    Number selectLastNumber(@Param("type") String type);
}