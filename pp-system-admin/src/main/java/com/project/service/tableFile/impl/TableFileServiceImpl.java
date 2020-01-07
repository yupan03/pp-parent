package com.project.service.tableFile.impl;

import com.project.service.CommonService;
import com.project.service.tableFile.TableFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableFileServiceImpl implements TableFileService {
    @Autowired
    private CommonService commonService;
}