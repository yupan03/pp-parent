package com.project.service.fileRecord.impl;

import com.project.constant.BusinessStatus;
import com.project.entity.dao.table.FileRecord;
import com.project.service.CommonService;
import com.project.service.fileRecord.FileRecordService;
import common.exception.BusinessException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;

@Service
public class FileRecordServiceImpl implements FileRecordService {
    @Autowired
    private CommonService commonService;

    @Override
    public FileRecord getById(Long id) {
        return commonService.getFileRecordDAO().selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void uploadFile(MultipartFile file, String attachmentPath, Integer type, Long userId) {
        try {
            File temp = new File(attachmentPath);
            if (!temp.exists()) {
                temp.mkdir();
            }
            String attachmentName = UUID.randomUUID().toString();
            String fileName = file.getOriginalFilename();


            String filePath = attachmentPath + File.separator + attachmentName;

            file.transferTo(new File(filePath));

            FileRecord fileRecord = new FileRecord();

            fileRecord.setAttachmentName(attachmentName);
            fileRecord.setAttachmentPath(attachmentPath);
            fileRecord.setFileName(fileName);
            fileRecord.setType(type);
            fileRecord.setOprId(userId);
            fileRecord.setInsertTime(new Date());
            fileRecord.setUpdateTime(new Date());
            commonService.getFileRecordDAO().insert(fileRecord);
        } catch (Exception e) {
            throw new BusinessException(BusinessStatus.ERROR_PARAM.status, "上传文件失败");
        }
    }

    @Override
    public void downloadFile(Long id, HttpServletResponse response) {
        try {
            FileRecord fileRecord = this.getById(id);
            FileInputStream fileInput = new FileInputStream(fileRecord.getAttachmentPath());
            byte[] content = IOUtils.toByteArray(fileInput);

            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String(fileRecord.getFileName().getBytes("gb2312"), "ISO8859-1"));
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("UTF-8");
            OutputStream output = response.getOutputStream();
            output.write(content);
            output.flush();
            fileInput.close();
            output.close();
        } catch (Exception e) {
            throw new BusinessException(BusinessStatus.ERROR_PARAM.status, "下载文件失败");
        }
    }
}