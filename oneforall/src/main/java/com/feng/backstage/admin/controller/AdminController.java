package com.feng.backstage.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.feng.backstage.base.BaseController;
import com.feng.backstage.exceptions.SystemExceptionEnum;
import com.feng.backstage.exceptions.GlobalException;
import com.feng.backstage.base.RequestObject;
import com.feng.backstage.base.ResponseObject;
import com.feng.backstage.admin.dto.JwtAuthenticationDTO;
import com.feng.backstage.exceptions.SystemException;
import com.feng.backstage.admin.dto.AdminLoginDTO;
import com.feng.backstage.admin.dto.AdminUpdatePasswordDTO;
import com.feng.backstage.admin.entity.Admin;
import com.feng.backstage.admin.service.AdminService;
import com.feng.backstage.utils.PasswordUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;
import java.util.Date;

/**
 * Create on 2019/10/23 9:14
 * @author Administrator
 */
@EnableSwagger2
@Api
@RestController
@EnableAutoConfiguration
@RequestMapping("/sys/sysUser")
public class AdminController extends GlobalException implements BaseController {

    private final static Logger logger = LoggerFactory.getLogger("SystemAdminController.class");

    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "登录", notes = "管理员登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    /**
     * @Valid对请求的参数进行校验
     */
    public ResponseObject<JwtAuthenticationDTO> login(@RequestBody @Valid RequestObject<AdminLoginDTO> requestObject) {
        // 获取前端输入的用户名和密码数据
        AdminLoginDTO adminLoginDTO = requestObject.getData();
        logger.info("loginDTO===========>" + JSONObject.toJSONString(adminLoginDTO));
        // 获取token
        JwtAuthenticationDTO tokenDTO = adminService.login(adminLoginDTO);
        return success(tokenDTO);
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ApiOperation(value = "跟新密码", notes = "管理员修改密码", consumes = "application/json")
    public ResponseObject updatePassword(@RequestBody @Valid RequestObject<AdminUpdatePasswordDTO> requestObject) {
        // 获取前端请求数据
        AdminUpdatePasswordDTO adminUpdatePasswordDTO = requestObject.getData();
        logger.info("用户输入的数据=======》" + adminUpdatePasswordDTO);
        // 获取当前用户
        Admin admin = adminService.getCurrentAdmin();
        logger.info("当前用户=====》" + admin);
        if (!PasswordUtil.verify(adminUpdatePasswordDTO.getOldPassword(), admin.getPassword())) {
            // 用户输入原密码与数据库加密的密码不一致，异常原密码错误
            throw new SystemException(SystemExceptionEnum.PASSWORD_ERROR.getCode(), SystemExceptionEnum.PASSWORD_ERROR.getMessage());
        }
        // 跟新密码
        admin.setPassword(PasswordUtil.generate(adminUpdatePasswordDTO.getNewPassword()));
        admin.setUtime(new Date());
        // updateById MybatisPlaus封装的方法
        adminService.updateById(admin);
        return success();
    }

}
