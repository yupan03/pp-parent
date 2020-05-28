package com.pp.dao;

import com.pp.entity.dto.TableFileDTO;
import com.pp.entity.table.TableFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TableFileDAO {
    int insert(TableFile record);

    /**
     * 改变 关系表的状态
     *
     * @param id   id
     * @param flag 状态 0：删除  1：正常
     * @return 受影响的行
     */
    int updateFlag(@Param("id") Long id, @Param("flag") byte flag);

    /**
     * 根据表查询附件
     *
     * @param tableName 表名
     * @param tableId   表ID
     * @param type      类别（各张表对应的字段可能有多个，需要使用这个字段做区分）
     * @return 返回记录
     */
    List<TableFileDTO> selectByTable(String tableName, Long tableId, Integer type);
}