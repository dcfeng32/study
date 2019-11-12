package com.feng.backstage.wechat.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * SHA1加密
 * Create on 2019/11/8 16:37
 * @author Administrator
 */
public class Sha1Util {
    /**
     *  String... arr可接受多个Object类型的参数
     * @param arr
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String gen(String... arr) throws NoSuchAlgorithmException {
        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();
        for (String a : arr) {
            sb.append(a);
        }
        MessageDigest sha1 = MessageDigest.getInstance("SHA1");
        sha1.update(sb.toString().getBytes());
        byte[] output = sha1.digest();
        return bytesToHex(output);
    }

    private static String bytesToHex(byte[] b) {
        char[]  hexDigit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            stringBuffer.append(hexDigit[(b[i] >> 4) & 0x0f]);
            stringBuffer.append(hexDigit[b[i] & 0x0f]);
        }
        return stringBuffer.toString();
    }

}
