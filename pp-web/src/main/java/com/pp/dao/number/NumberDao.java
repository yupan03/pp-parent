package com.pp.dao.number;

import com.pp.entity.tables.Number;
import org.apache.ibatis.annotations.Param;

public interface NumberDao {
    Number selectLastNumber(@Param("type") String type);
}