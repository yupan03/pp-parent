package com.pp.service.tableFile.impl;

import com.pp.common.exception.BusinessException;
import com.pp.constant.BusinessStatus;
import com.pp.entity.table.FileRecord;
import com.pp.entity.table.TableFile;
import com.pp.service.CommonService;
import com.pp.service.fileRecord.FileRecordService;
import com.pp.service.tableFile.TableFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TableFileServiceImpl implements TableFileService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private FileRecordService fileRecordService;

    @Override
    public void addTableFile(String tableName, Long tableId, Integer type, Long fileRecordId) {
        FileRecord fileRecord = fileRecordService.getById(fileRecordId);
        if (fileRecord == null) {
            throw new BusinessException(BusinessStatus.ERROR_PARAM.status, "附件不能为空");
        }
        TableFile tableFile = new TableFile();

        tableFile.setTableName(tableName);
        tableFile.setTableId(tableId);
        tableFile.setTableId(fileRecord.getId());
        tableFile.setTableName(fileRecord.getFileName());
        tableFile.setType(type);
        tableFile.setFlag(1);
        tableFile.setCreateTime(new Date());

        commonService.getTableFileDAO().insert(tableFile);
    }

    @Override
    public void updateTableFile(Long id, Integer flag) {
        commonService.getTableFileDAO().updateFlag(id, flag);
    }
}