package com.feng.backstage.admin.dto;

import java.io.Serializable;

/**
 * Create on 2019/10/24 13:13
 * @author Administrator
 */
public class AdminUpdatePasswordDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String oldPassword;

    /**
     * 前端需要输入两次新密码，一致时才能修改成功。前端验证，后台不处理
     */
    private String newPassword;

    public static long getSerialVersionUid() {
        return serialVersionUID;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public AdminUpdatePasswordDTO(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "AdminUpdatePasswordDTO{" +
                "oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
