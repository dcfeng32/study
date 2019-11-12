package com.feng.backstage.admin.service.serviceimpl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.feng.backstage.base.Constants;
import com.feng.backstage.admin.dto.JwtAuthenticationDTO;
import com.feng.backstage.admin.dto.AdminLoginDTO;
import com.feng.backstage.base.SystemUserStatus;
import com.feng.backstage.exceptions.SystemException;
import com.feng.backstage.exceptions.SystemExceptionEnum;
import com.feng.backstage.admin.dao.AdminDao;
import com.feng.backstage.admin.entity.Admin;
import com.feng.backstage.admin.service.AdminService;
import com.feng.backstage.utils.ParamUtil;
import com.feng.backstage.utils.PasswordUtil;
import com.feng.backstage.utils.httputils.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

/**
 * Create on 2019/10/23 10:38
 * @author Administrator
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminDao, Admin> implements AdminService {

    private static final Logger logger = LoggerFactory.getLogger("SystemUserServiceImpl.class");

    @Autowired
    private SimpleCacheManager simpleCacheManager;

    @Autowired
    private AdminDao adminDao;

    @Override
    public JwtAuthenticationDTO login(AdminLoginDTO adminLoginDTO) {
        EntityWrapper<Admin> wrapper = new EntityWrapper<>();
        Admin admin = new Admin();
        admin.setUsername(adminLoginDTO.getUsername());
        wrapper.setEntity(admin);
        logger.info("wapper==================>" + JSONObject.toJSONString(wrapper));
        // EntityWrapper提供的方法
        admin = selectOne(wrapper);
        // 判断查找结果
        if (ParamUtil.isNullOrEmptyOrZero(admin)) {
            throw new SystemException(SystemExceptionEnum.USER_NOT_EXIST.getCode(), SystemExceptionEnum.USER_NOT_EXIST.getMessage());
        }
        if (Objects.equals(admin.getIsEnable(), SystemUserStatus.DISABLE.getCode())) {
            throw new SystemException(SystemExceptionEnum.USER_DISABLED.getCode(), SystemExceptionEnum.USER_DISABLED.getMessage());
        }
        // 获取数据库用MD5加密后的密码
        String md5Pass = admin.getPassword();
        // 获取前台输入密码
        String password = adminLoginDTO.getPassword();
        // 密码校验
        if (!PasswordUtil.verify(password, md5Pass)) {
            throw new SystemException(SystemExceptionEnum.ILLEGAL_USER.getCode(), SystemExceptionEnum.ILLEGAL_USER.getMessage());
        }
        // 密码校验通过后加入缓存
        String accessToken = UUID.randomUUID().toString();
        logger.info("accessToken==============>" + accessToken);
        try {
            Cache cache = simpleCacheManager.getCache(Constants.ACCESS_TOKEN_CACHE_NAME);
            logger.info("cache=========>" + JSONObject.toJSONString(cache));
            cache.put(accessToken, admin);
            logger.info("accessToken,admin加入cache后====》" + JSONObject.toJSONString(cache));
        } catch (Exception e) {
            throw new SystemException("00000", "cache异常");
        }
        return new JwtAuthenticationDTO(accessToken);
    }

    @Override
    public Admin getCurrentAdmin() {
        // 获取的header为空？？？
        String header = WebUtil.getHeader(Constants.TOKEN_HEADER_NAME);
        logger.info("header是accessToken=====》" + JSONObject.toJSONString(header));
        Admin admin = getAdminByAccessToken(header);
        return admin;
    }

    @Override
    public Admin getAdminByAccessToken(String accessToken) {
        if (ParamUtil.isNullOrEmptyOrZero(accessToken)) {
            // accessToken为空，异常参数不合法
            throw new SystemException(SystemExceptionEnum.ILLEGAL_PARAM.getCode(), SystemExceptionEnum.ILLEGAL_PARAM.getMessage());
        }
        // 获取缓存的数据
        Cache cache = simpleCacheManager.getCache(Constants.ACCESS_TOKEN_CACHE_NAME);
        logger.info("cache======>" + JSONObject.toJSONString(cache));
        Admin admin = cache.get(accessToken, Admin.class);
        if (Objects.isNull(admin)) {
            // 缓存中没有数据，未登录异常
            throw new SystemException(SystemExceptionEnum.NO_LOGIN.getCode(), SystemExceptionEnum.NO_LOGIN.getMessage());
        }
        return admin;
    }

}
