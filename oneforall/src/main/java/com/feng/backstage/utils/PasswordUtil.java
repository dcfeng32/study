package com.feng.backstage.utils;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.util.Random;

/**
 * MD5加密与密码校验
 * Create on 2019/10/23 10:58
 * @author Administrator
 */
public class PasswordUtil {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger("PasswordUtil.class");

    /**
     * 生成密码
     *
     * @param password
     * @return
     */
    public static String generate(String password) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder(16);
        sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
        int len = sb.length();
        int displace = 16;
        if (len < displace) {
            for (int i = 0; i < displace - len; i++) {
                sb.append("0");
            }
        }
        String salt = sb.toString();
        password = md5Hex(password + salt);
        char[] cs = new char[48];
        int parityBit = 48;
        int move = 3;
        for (int i = 0; i < parityBit; i = i + move) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        // 与返回的cs一致
        System.out.println("dedao de cs是" + new String(cs));
        // cs为char[]型，返回需要相应的类型
        return new String(cs);
    }

    /**
     * 获取十六进制字符串形式的MD5摘要
     *
     * @param sr
     * @return
     */
    private static String md5Hex(String sr) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bs = md5.digest(sr.getBytes());
            return new String(new Hex().encode(bs));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 校验密码
     *
     * @param password
     * @param md5
     * @return
     */
    public static boolean verify(String password, String md5) {
        int parityBit = 48;
        int move = 3;
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < parityBit; i += move) {
            cs1[i / 3 * 2] = md5.charAt(i);
            cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
            cs2[i / 3] = md5.charAt(i + 1);
        }
        String salt = new String(cs2);
        return md5Hex(password + salt).equals(new String(cs1));
    }

//    //main测试
//    public static void main(String[] args) {
//        String password = generate("admin");
//        logger.info("生成md5密码=======>" + password);
//        // 验证密码
//        boolean result = verify("admin", password);
//        System.out.println(result);
//    }

}
