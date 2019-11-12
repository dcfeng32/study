package com.feng.backstage.device.service;

import com.feng.backstage.base.ResponseObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Administrator
 */
@Service
public interface DeviceService {
    /**
     * 导入设备
     * @param productKey
     * @param inputStream
     * @param type
     * @return
     * @throws IOException
     */
    ResponseObject saveDetectorFromExcel(String productKey, InputStream inputStream, String type) throws IOException;


}
