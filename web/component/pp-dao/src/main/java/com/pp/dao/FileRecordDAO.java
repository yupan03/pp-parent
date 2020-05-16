package com.pp.dao;

import com.pp.entity.dao.table.FileRecord;

public interface FileRecordDAO {
    int insert(FileRecord record);

    FileRecord selectByPrimaryKey(Long id);
}