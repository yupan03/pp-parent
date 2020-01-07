package com.project.entity.dao.table;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * table_file
 *
 * @author
 */
@Data
public class TableFile implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String tableName;

    private Long tableId;

    private Long fileRecordId;

    private String fileName;

    private Integer type;

    private Integer flag;

    private Date createTime;
}