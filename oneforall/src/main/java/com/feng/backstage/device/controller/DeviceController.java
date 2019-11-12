package com.feng.backstage.device.controller;

import com.feng.backstage.base.BaseController;
import com.feng.backstage.base.ResponseObject;
import com.feng.backstage.exceptions.SystemExceptionEnum;
import com.feng.backstage.device.service.DeviceService;
import com.feng.backstage.utils.ParamUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.io.InputStream;


/**
 * 设备导入授权，查找、分页
 * Create on 2019/10/26 14:24
 * @author Administrator
 */
@Api
@EnableSwagger2
@RestController
@EnableAutoConfiguration
@RequestMapping("/device")
public class DeviceController implements BaseController {

    private static final Logger logger = LoggerFactory.getLogger("DeviceController.class");

    @Autowired
    private DeviceService deviceService;

    @ApiOperation(value = "excel上传", notes = "mac授权与保存")
    @RequestMapping(value = "/shouquan", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
    public ResponseObject addDevice(@RequestParam MultipartFile file) {
        ResponseObject responseObject = new ResponseObject(SystemExceptionEnum.ILLEGAL_PARAM.getCode(), "请选择上传的文件");
        // 文件异常
        if (ParamUtil.isNullOrEmptyOrZero(file)) {
            return ResponseObject.response(SystemExceptionEnum.ILLEGAL_PARAM);
        }
        // 判断文件类型，仅仅支持xls或者xlsx格式
        String xlsType = "";
        String xlsx = ".xlsx";
        String xls = ".xls";
        if (file.getOriginalFilename().endsWith(xlsx)) {
            xlsType = ".xlsx";
        } else if (file.getOriginalFilename().endsWith(xls)) {
            xlsType = ".xls";
        } else {
            return responseObject;
        }
        try {
            InputStream inputStream = file.getInputStream();
            return deviceService.saveDetectorFromExcel("13456789", inputStream, xlsType);
        } catch (IOException e) {
            logger.error("文件上传异常", e);
            responseObject.setMessage("文件上传异常，请重试");
            return responseObject;
        }
    }

}
