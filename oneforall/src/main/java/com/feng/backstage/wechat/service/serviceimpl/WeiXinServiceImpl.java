package com.feng.backstage.wechat.service.serviceimpl;

import com.feng.backstage.wechat.service.WeixinService;
import com.feng.backstage.wechat.util.Sha1Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

/**
 * Create on 2019/11/7 10:20
 * @author Administrator
 */
@Service
public class WeiXinServiceImpl implements WeixinService {

    private static final Logger logger = LoggerFactory.getLogger(WeiXinServiceImpl.class);

    private static final String WEIXIN_TOKEN = "dongfang";


    @Override
    public boolean veritySignature(String signature, String timestamp, String nonce) {
        try {
            if (signature.equals(Sha1Util.gen(WEIXIN_TOKEN, timestamp, nonce))) {
                return true;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.info("微信验证失败！");
            return false;
        }
        return false;
    }
}
