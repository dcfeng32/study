package com.feng.backstage.device.service.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.feng.backstage.base.ResponseObject;
import com.feng.backstage.exceptions.ErrorMsgEnum;
import com.feng.backstage.device.dao.DeviceDao;
import com.feng.backstage.admin.entity.Admin;
import com.feng.backstage.device.entity.Device;
import com.feng.backstage.product.entity.Product;
import com.feng.backstage.admin.service.AdminService;
import com.feng.backstage.device.service.DeviceService;
import com.feng.backstage.product.service.ProductService;
import com.feng.backstage.utils.DateKit;
import com.feng.backstage.utils.ExcelReadUtil;
import com.feng.backstage.utils.ParamUtil;
import com.feng.backstage.device.vo.DeviceAuth;
import com.feng.backstage.wechat.util.QrcodeUtil;
import com.feng.backstage.wechat.util.WeixinApi;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 处理Excel内容，读取设备信息授权并保存
 * Create  on 2019/10/26 15:51
 *
 * @author Administrator
 */
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceDao, Device> implements DeviceService {

    public final static int FIRST_ROW = 3;

    private final static Logger logger = LoggerFactory.getLogger("DetectorServiceImpl.class");

    @Autowired
    public AdminService adminService;

    @Autowired
    private ProductService productService;

    @Override
    public ResponseObject saveDetectorFromExcel(String productKey, InputStream inputStream, String xlsType) {
        Workbook workbook = ExcelReadUtil.handleExcel(inputStream, xlsType);
        // 需要添加到数据库的设备
        List<Device> deviceList = new ArrayList<>();
        // 不能导入的mac列表
        List<Map> mapList = new ArrayList<>();
        if (workbook == null) {
            return null;
        }

        // 获取第一个sheet
        Sheet sheet = workbook.getSheetAt(0);
        //获取总行数
        int rows = sheet.getPhysicalNumberOfRows();
        // 通过token获取当前用户
        Admin currentAdmin = adminService.getCurrentAdmin();
        if (ParamUtil.isNullOrEmptyOrZero(currentAdmin)) {
            return ResponseObject.response(ErrorMsgEnum.USER_DONT_EXISTS);
        }

        Product product = null;
        // 通过PK，获取产品信息
        product = productService.findOneByProductKey(productKey);
        if (ParamUtil.isNullOrEmptyOrZero(product)) {
            return ResponseObject.response(ErrorMsgEnum.PRODUCT_DONT_EXISTS);
        }

        // 获取当前时间
        String now = DateKit.getTimestampString(new Date());
        logger.info("入库读取文件开始：" + now);
        int rowMacNum = 4;
        if (rows >= rowMacNum) {
            for (int i = FIRST_ROW; i < rows; i++) {
                //获取第i行
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                // 获取mac
                if (ParamUtil.isNullOrEmptyOrZero(row.getCell(0).getCellStyle())) {
                    continue;
                }
                String mac = row.getCell(0).toString();
                if (ParamUtil.isNullOrEmptyOrZero(mac)) {
                    continue;
                }
                // mac转小写保存
                mac = mac.toLowerCase();
                Device device = new Device();

                // 判断是否已经存在mac
                if (checkMacIsExists(mac, productKey)) {
                    // 返回到外面的mac地址
                    logger.error("重复入库：" + mac);
                    continue;
                } else {

                }
                // 将信息保存在数据
                saveDetector(product, productKey, currentAdmin, mac, device);
                // 设备授权
                device = createQrcodeAndAuthDevice(device);
                deviceList.add(device);
            }
        }
        return null;
    }

    /**
     * 获取设备二维码及设备授权
     *
     * @param device
     * @return
     */
    private Device createQrcodeAndAuthDevice(Device device) {
        // 调用微信接口获取设备二维码
        Map<String, String> map = QrcodeUtil.createQrcodeFromWeixin(device);
        device.setWxDid(String.valueOf(map.get("wxDid")));
        device.setWxTicket(String.valueOf(map.get("wxTicket")));
        device.setQrCode(String.valueOf(map.get("wxTicket")));
        logger.error("wedid:" + map.get("wxDid"));
        // 设备授权结果
        deviceAuthResult(device);
        return device;
    }

    /**
     * 设备授权
     *
     * @param device
     */
    private int deviceAuthResult(Device device) {
        List<DeviceAuth> deviceAuths = new ArrayList<>();
        String mac = device.getMac().substring(device.getMac().length() - 12);
        // 微信设备授权不区分大小写
        mac = mac.toUpperCase();
        DeviceAuth deviceAuth = new DeviceAuth(device.getWxDid(), mac);
        deviceAuths.add(deviceAuth);

        String res = authorizeDevice(deviceAuths);
        logger.info("设备：" + mac + "," + device.getWxDid() + "  微信授权结果：" + res);
        JSONObject jsonRes = JSON.parseObject(res);
        int errcode = 1;
        String resp = "";
        if (jsonRes.containsKey(resp)) {
            errcode = jsonRes.getJSONArray("resp").getJSONObject(0).getInteger("errcode");
        }
        return errcode;
    }

    /**
     * 微信授权参数，详情见微信硬件开发文档-设备授权
     *
     * @param deviceAuth
     * @return
     */
    private String authorizeDevice(List<DeviceAuth> deviceAuth) {
        JSONObject json = new JSONObject();
        json.put("device_num", String.valueOf(deviceAuth.size()));
        // 旧接口为0，并且put product_id
        json.put("op_type", 0);
        // 旧接口put product_id
        json.put("product_id", "54814");
        json.put("device_list", deviceAuth);
        logger.info("授权请求参数=======>" + json.toString());
        return WeixinApi.getAuthorizeDeviceUrl(json.toString());
    }

    /**
     * 设备信息保存在数据库
     *
     * @param product
     * @param productKey
     * @param currentAdmin
     * @param mac
     * @param device
     */
    private void saveDetector(Product product, String productKey, Admin currentAdmin, String mac, Device device) {
        Date nowDate = new Date();
        device.setDeviceName("保险柜");
        device.setProductId(product.getId());
        device.setProductKey(productKey);
        device.setProductName(product.getProductName());
        device.setMac(mac);
        device.setDeviceType(product.getProductName());
        device.setCreateorId(currentAdmin.getId());
        device.setCreateTime(nowDate);
        device.setModifyTime(nowDate);
        // 授权时默认设备状态离线
        device.setOnlineStatus(1);
    }

    /**
     * 根据mac和PK判断产品是否存在
     *
     * @param mac
     * @param productKey
     * @return
     */
    private boolean checkMacIsExists(String mac, String productKey) {
        EntityWrapper wrapper = new EntityWrapper();
        Device device = new Device();
        device.setProductKey(productKey);
        device.setMac(mac);
        device.setDeleted(0);
        wrapper.setEntity(device);
        // 需要类继承ServiceImpl才能使用该方法
        Device exist = selectOne(wrapper);
        if (ParamUtil.isNullOrEmptyOrZero(exist)) {
            return false;
        }
        return true;
    }

}
