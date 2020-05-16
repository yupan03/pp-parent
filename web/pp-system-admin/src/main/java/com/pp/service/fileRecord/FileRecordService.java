package com.pp.service.fileRecord;

import com.pp.entity.dao.table.FileRecord;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface FileRecordService {
    FileRecord getById(Long id);

    @Transactional
    void uploadFile(MultipartFile file, Integer type, Long userId);

    void downloadFile(Long id, HttpServletResponse response);
}