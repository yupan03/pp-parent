package com.project.service.tableFile.impl;

import com.project.constant.BusinessStatus;
import com.project.constant.DeleteEnum;
import com.project.entity.dao.table.FileRecord;
import com.project.entity.dao.table.TableFile;
import com.project.service.CommonService;
import com.project.service.fileRecord.FileRecordService;
import com.project.service.tableFile.TableFileService;
import common.exception.BusinessException;
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
        tableFile.setFlag(DeleteEnum.NORMAL.value);
        tableFile.setCreateTime(new Date());

        commonService.getTableFileDAO().insert(tableFile);
    }

    @Override
    public void updateTableFile(Long id, Integer flag) {
        commonService.getTableFileDAO().updateFlag(id, flag);
    }
}