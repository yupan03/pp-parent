package com.project.service.fileRecord.impl;

import com.project.service.CommonService;
import com.project.service.fileRecord.FileRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileRecordServiceImpl implements FileRecordService {
    @Autowired
    private CommonService commonService;

    @Override
    public void uploadFile() {

    }

    @Override
    public void downloadFile() {

    }
}