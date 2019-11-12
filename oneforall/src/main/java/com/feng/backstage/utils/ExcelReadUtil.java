package com.feng.backstage.utils;


import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * 处理Excel工具类
 * Create on 2019/10/26 16:00
 * @author Administrator
 */
public class ExcelReadUtil {

    private static final Logger logger = LoggerFactory.getLogger("ExcelReadUtil.class");

    /**
     * 传入mac、ProductKey和excel类型，处理excel内容
     * @param inputStream
     * @param xlsType
     * @return
     */
    public static Workbook handleExcel(InputStream inputStream, String xlsType) {
        Workbook workbook = null;
        try {
            String xls = "xls";
            if (StringUtils.endsWith(xlsType, xls)) {
                workbook = new HSSFWorkbook(inputStream);
            }

            String xlsx = "xlsx";
            if (StringUtils.endsWith(xlsType, xlsx)) {
                workbook = new XSSFWorkbook(inputStream);
            }
            // 正常情况下返回workbook,若发生异常会执行catch、finally
            return workbook;
        } catch (IOException e) {
            logger.warn("读取文件异常", e);
        } finally {
            if (inputStream == null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.warn("inputStarm关闭失败！！！");
                }
            }
        }
        return workbook;
    }
}
