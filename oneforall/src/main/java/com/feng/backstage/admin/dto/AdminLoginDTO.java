package com.feng.backstage.admin.dto;


import java.io.Serializable;

/**
 * 需要实现序列化的情况：
 * 当你想把的内存中的对象写入到硬盘的时候。
 * 当你想用套接字在网络上传送对象的时候。
 * 当你想通过RMI传输对象的时候。
 * Create on 2019/10/23 9:31
 *
 * @author Administrator
 */
public class AdminLoginDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
