package com.feng.backstage.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.feng.backstage.admin.dto.JwtAuthenticationDTO;
import com.feng.backstage.admin.dto.AdminLoginDTO;
import com.feng.backstage.admin.entity.Admin;
import org.springframework.stereotype.Service;

/**
 * Create on 2019/10/23 10:07
 *
 * @author Administrator
 */
@Service
public interface AdminService extends IService<Admin> {

    /**
     * admin用户登录
     *
     * @param adminLoginDTO
     * @return
     */
    JwtAuthenticationDTO login(AdminLoginDTO adminLoginDTO);

    /**
     * 获取当前用户
     *
     * @return Admin
     */
    Admin getCurrentAdmin();

    /**
     * 根据缓存token获取用户
     *
     * @param accessToken
     * @return
     */
    Admin getAdminByAccessToken(String accessToken);

}
