package com.project.entity.dao.table;

import java.io.Serializable;
import java.util.Date;

/**
 * file_record
 * @author 
 */
public class FileRecord implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 附件uuid
     */
    private String attachmentName;

    /**
     * 附件路径
     */
    private String attachmentPath;

    /**
     * 类型：0.excel下载  1.文件上传
     */
    private Byte type;

    /**
     * 操作人
     */
    private Long oprId;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 插入时间
     */
    private Date insertTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Long getOprId() {
        return oprId;
    }

    public void setOprId(Long oprId) {
        this.oprId = oprId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
}