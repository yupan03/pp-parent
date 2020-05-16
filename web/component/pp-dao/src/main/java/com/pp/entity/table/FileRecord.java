package com.pp.entity.table;

import lombok.Data;

import java.util.Date;

/**
 * file_record
 *
 * @author
 */
@Data
public class FileRecord {
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
	 * 类型：0.excel下载 1.文件上传
	 */
	private Integer type;

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

}