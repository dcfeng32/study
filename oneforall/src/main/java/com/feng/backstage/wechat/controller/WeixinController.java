package com.feng.backstage.wechat.controller;

import com.feng.backstage.base.BaseController;
import com.feng.backstage.wechat.service.WeixinService;
import com.feng.backstage.wechat.util.WeixinApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletResponse;

/**
 * 微信相关
 * Create on 2019/11/7 9:44
 * @author Administrator
 */
@EnableSwagger2
@Api
@EnableAutoConfiguration
@RestController
@RequestMapping("/weixin")
public class WeixinController implements BaseController {

    private static final Logger logger = LoggerFactory.getLogger(WeixinController.class);

    @Autowired
    private WeixinService weixinService;

    @ApiOperation(value = "校验微信信息")
    @RequestMapping(value = "/weixin", method = RequestMethod.GET)
    public void weixinCheck(
            @RequestParam(value = "signature", required = false) @ApiParam(value = "微信签名") String signature,
            @RequestParam(value = "timestamp", required = false) @ApiParam(value = "时间戳") String timestamp,
            @RequestParam(value = "nonce", required = false) @ApiParam(value = "随机数") String nonce,
            @RequestParam(value = "echostr", required = false) @ApiParam(value = "随机字符串") String echostr,
            HttpServletResponse response) {
        if (weixinService.veritySignature(signature, timestamp, nonce)) {
            WeixinApi.out(echostr, response);
        }
    }




}
