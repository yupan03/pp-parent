package com.pp.entity.table;

import lombok.Data;

import java.util.Date;

/**
 * table_file
 *
 * @author
 */
@Data
public class TableFile {

	private Long id;

	private String tableName;

	private Long tableId;

	private Long fileRecordId;

	private String fileName;

	private Integer type;

	private Integer flag;

	private Date createTime;
}