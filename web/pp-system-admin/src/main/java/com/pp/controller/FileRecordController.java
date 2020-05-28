package com.pp.controller;

import com.pp.common.result.Result;
import com.pp.constant.DeleteStatusEnum;
import com.pp.service.fileRecord.FileRecordService;
import com.pp.service.tableFile.TableFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = {"/file"})
public class FileRecordController extends BaseController {
    @Autowired
    private FileRecordService fileRecordService;
    @Autowired
    private TableFileService tableFileService;

    @GetMapping(value = {"/download/{id}"})
    public void download(HttpServletResponse response, @PathVariable("id") Long id) {
        fileRecordService.downloadFile(id, response);
    }

    @PostMapping(value = {"/delete/{id}"})
    public Result delete(@PathVariable("id") Long id) {
        tableFileService.updateTableFile(id, DeleteStatusEnum.DELETE.getValue());
        return super.result(HttpStatus.OK.value(), "删除成功");
    }


    @PostMapping(value = {"/upload"})
    public Result upload(MultipartFile file, Integer type) {
        fileRecordService.uploadFile(file, type, 123L);
        return super.result(HttpStatus.OK.value(), "上传成功");
    }
}