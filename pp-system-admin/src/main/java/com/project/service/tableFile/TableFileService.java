package com.project.service.tableFile;

public interface TableFileService {
    void addTableFile(String tableName, Long tableId, Integer type, Long fileRecordId);

    void updateTableFile(Long id, Integer flag);
}