package com.feng.backstage.admin.dto;

/**
 * Create on 2019/10/25 10:50
 *
 * @author Administrator
 */
public class AdminJwtAuthenticationDTO extends JwtAuthenticationDTO {

    private final Integer userId;

    public AdminJwtAuthenticationDTO(String token, Integer userId) {
        super(token);
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "AdminJwtAuthenticationDTO{" +
                "userId=" + userId +
                '}';
    }
}
