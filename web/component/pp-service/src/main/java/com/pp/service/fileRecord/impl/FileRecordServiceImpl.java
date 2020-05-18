package com.pp.service.fileRecord.impl;

import com.pp.common.exception.BusinessException;
import com.pp.common.utils.IdUtil;
import com.pp.constant.BusinessStatus;
import com.pp.entity.table.FileRecord;
import com.pp.service.CommonService;
import com.pp.service.fileRecord.FileRecordService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.UUID;

@Service
public class FileRecordServiceImpl implements FileRecordService {
    @Value("${file_path}")
    private String filePath;
    @Autowired
    private CommonService commonService;

    @Override
    public FileRecord getById(Long id) {
        return commonService.getFileRecordDAO().selectByPrimaryKey(id);
    }

    @Override
    public void downloadFile(Long id, HttpServletResponse response) {
        try {
            FileRecord fileRecord = this.getById(id);
            FileInputStream fileInput = new FileInputStream(fileRecord.getAttachmentPath() + File.separator
                    + fileRecord.getAttachmentName());
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
            e.printStackTrace();
            throw new BusinessException(BusinessStatus.ERROR_PARAM.status, "下载文件失败");
        }
    }

    @Override
    @Transactional
    public void uploadFile(MultipartFile file, Integer type, Long userId) {
        String attachmentPath = getAttachmentPath(type);
        try {
            File temp = new File(attachmentPath);
            if (!temp.exists()) {
                temp.mkdirs();
            }
            String attachmentName = UUID.randomUUID().toString();
            String fileName = file.getOriginalFilename();


            String filePath = attachmentPath + File.separator + attachmentName;

            file.transferTo(new File(filePath));

            FileRecord fileRecord = new FileRecord();

            fileRecord.setId(IdUtil.getId());
            fileRecord.setAttachmentName(attachmentName);
            fileRecord.setAttachmentPath(attachmentPath);
            fileRecord.setFileName(fileName);
            fileRecord.setType(1);
            fileRecord.setOprId(userId);
            fileRecord.setInsertTime(new Date());
            fileRecord.setUpdateTime(new Date());

            commonService.getFileRecordDAO().insert(fileRecord);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(BusinessStatus.ERROR_PARAM.status, "上传文件失败");
        }
    }

    private String getAttachmentPath(Integer type) {
        String attachmentPath = filePath + File.separator;
        switch (type) {
            case 1:
                attachmentPath += "idCard";
                break;
            default:
                attachmentPath += "temp";
        }

        return attachmentPath;
    }
}