package com.feng.backstage.wechat.service;

import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public interface WeixinService {

    /**
     *  微信校验
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    boolean veritySignature(String signature, String timestamp, String nonce);


}
