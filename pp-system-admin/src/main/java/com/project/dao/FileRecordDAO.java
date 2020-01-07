package com.project.dao;

import com.project.entity.dao.table.FileRecord;

public interface FileRecordDAO {
    int insert(FileRecord record);

    FileRecord selectByPrimaryKey(Long id);
}