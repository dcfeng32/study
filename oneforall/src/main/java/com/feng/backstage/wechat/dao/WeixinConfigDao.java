package com.feng.backstage.wechat.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.feng.backstage.wechat.entity.WxConfig;

/**
 * @author Administrator
 */
public interface WeixinConfigDao extends BaseMapper<WxConfig> {

    /**
     * 查找wx_cogfig所有数据
     * @return wxConfig
     */
    WxConfig findOneByLimitOne();

}
