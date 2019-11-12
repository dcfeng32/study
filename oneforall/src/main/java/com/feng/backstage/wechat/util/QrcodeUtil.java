package com.feng.backstage.wechat.util;

import com.alibaba.fastjson.JSONObject;
import com.feng.backstage.device.entity.Device;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生成并保存设备二维码
 * Create on 2019/11/9 9:01
 *
 * @author Administrator
 */
public class QrcodeUtil {

    private static Logger logger = LoggerFactory.getLogger("QrcodeUtil");

    public static Map<String, String> createQrcodeFromWeixin(Device device) {
        String accessToken = AccessTokenUtil.refreshAndGetToken();
        if (StringUtils.isEmpty(accessToken)) {
            logger.error("获取微信二维码失败，无法获取accessToken!");
        }

        List<String> deviceIdList = new ArrayList<>();
        deviceIdList.add(device.getMac().toUpperCase());
        JSONObject body = new JSONObject();
        body.put("device_num", 1);
        body.put("device_id_list", deviceIdList);

        String res = WeixinApi.createQrcode(accessToken, body.toString());
        // 如果res返回失败
        if (!getDeviceIdTicker(res)) {
            logger.error("获取微信二维码时，获取device_id  ticket失败");
        }

        JSONObject resJson = JSONObject.parseObject(res);
        JSONObject codeTicket = resJson.getJSONArray("code_list").getJSONObject(0);
        String ticket = String.valueOf(codeTicket.get("ticket"));
        String wxDid = String.valueOf(codeTicket.get("device_id"));
        // 初始化hashMap容量
        Map<String, String> map = new HashMap<>(6);
        map.put("wxDid", wxDid);
        map.put("wxTicket", ticket);
        return map;
    }

    private static boolean getDeviceIdTicker(String res) {
        try {
            JSONObject object = JSONObject.parseObject(res);
            String codeList = "code_list";
            String errCode = "errcode";
            if (!object.containsKey(codeList)) {
                logger.warn("==>etDeviceIdTicket 获取device_id,ticket时错误：" + res);
                return false;
            }
            if (object.containsKey(errCode) && (object.getIntValue(errCode) == 0)) {
                return true;
            } else {
                logger.warn("==>etDeviceIdTicket 获取device_id,ticket时错误：" + res);
                return false;
            }
        } catch (Exception e) {
            logger.error("==>getDeviceIdTicket 获取device_id,ticket时异常", e);
            return false;
        }
    }
}
