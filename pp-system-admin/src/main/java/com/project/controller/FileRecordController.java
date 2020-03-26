package com.project.controller;

import com.project.constant.DeleteEnum;
import com.project.service.fileRecord.FileRecordService;
import com.project.service.tableFile.TableFileService;
import common.result.Result;
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

    /**
     * @api {GET} /{"/file"}/{"/download/{id}"} download
     * @apiVersion 1.0.0
     * @apiGroup FileRecordController
     * @apiName download
     * @apiDescription 上传接口
     * @apiParam (请求参数) {Number} id
     * @apiParamExample 请求参数示例
     * id=84
     * @apiSuccess (响应结果) {Object} response
     * @apiSuccessExample 响应结果示例
     * null
     */
    @GetMapping(value = {"/download/{id}"})
    public void download(HttpServletResponse response, @PathVariable("id") Long id) {
        fileRecordService.downloadFile(id, response);
    }

    @PostMapping(value = {"/delete/{id}"})
    public Result delete(@PathVariable("id") Long id) {
        tableFileService.updateTableFile(id, DeleteEnum.DELETE.value);
        return super.result(HttpStatus.OK.value(), "删除成功");
    }

    /**
     * @api {POST} /{"/file"}/{"/upload"} upload
     * @apiVersion 1.0.0
     * @apiGroup FileRecordController
     * @apiName upload
     * @apiParam (请求参数) {Number} type
     * @apiParamExample 请求参数示例
     * type=237
     * @apiSuccess (响应结果) {Number} status
     * @apiSuccess (响应结果) {String} msg
     * @apiSuccessExample 响应结果示例
     * {"msg":"TIIS","status":6906}
     */
    @PostMapping(value = {"/upload"})
    public Result upload(MultipartFile file, Integer type) {
        fileRecordService.uploadFile(file, type, 123L);
        return super.result(HttpStatus.OK.value(), "上传成功");
    }
}