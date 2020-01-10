package com.project.controller;

import com.project.constant.DeleteEnum;
import com.project.service.fileRecord.FileRecordService;
import com.project.service.tableFile.TableFileService;
import common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@Api(tags = "附件管理")
@RequestMapping(value = {"/file"})
public class FileRecordController extends BaseController {
    @Autowired
    private FileRecordService fileRecordService;
    @Autowired
    private TableFileService tableFileService;

    @ApiOperation(value = "/download/{id}", tags = "下载文件")
    @GetMapping(value = {"/download/{id}"})
    public void download(HttpServletResponse response, @PathVariable("id") Long id) {
        fileRecordService.downloadFile(id, response);
    }

    @ApiOperation(value = "/delete/{id}", tags = "删除文件")
    @PostMapping(value = {"/delete/{id}"})
    public Result delete(@PathVariable("id") Long id) {
        tableFileService.updateTableFile(id, DeleteEnum.DELETE.value);
        return super.result(HttpStatus.OK.value(), "删除成功");
    }

    @ApiOperation(value = "/upload", tags = "上传文件")
    @PostMapping(value = {"/upload"})
    public Result upload(MultipartFile file, Integer type) {
        fileRecordService.uploadFile(file, type, 123L);
        return super.result(HttpStatus.OK.value(), "上传成功");
    }
}