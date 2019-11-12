package com.feng.backstage.admin.dto;

import java.io.Serializable;

/**
 * Create on 2019/10/23 9:28
 * @author Administrator
 */
public class JwtAuthenticationDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String token;

    public JwtAuthenticationDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "JwtAuthenticationDTO{" +
                "token='" + token + '\'' +
                '}';
    }
}
