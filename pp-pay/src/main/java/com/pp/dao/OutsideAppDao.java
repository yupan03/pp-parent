package com.pp.dao;

import com.pp.entity.table.OutsideApp;
import org.apache.ibatis.annotations.Param;

/**
 * 这个只负责查询，专门维护在另外的管理系统中
 */
public interface OutsideAppDao {
	OutsideApp selectByAppAndSecret(@Param("app") String app, @Param("secret") String secret);
}