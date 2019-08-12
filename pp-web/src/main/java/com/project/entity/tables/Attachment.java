package com.project.entity.tables;

import lombok.Data;

@Data
public class Attachment {
    private Integer id;

    private String uuid;
    private String createTime;
    private String relationUuid;
    private String relationTable;
    private String fileType;
    private String fileName;
    private String attachmentName;
    private String attachmentPath;
    private String attachmentStatus;
    private String uploader;
    private String uploadTime;
}