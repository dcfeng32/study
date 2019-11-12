package com.feng.backstage.login;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class BackstageApplicationTests {

    @Test
    void contextLoads() {
    }



    public static void main(String[] args) {
        int result = integerInversion(5269);
        System.out.println(result);
    }

    public static int integerInversion(int num) {
        int resNum = 0;
        while (num != 0) {
            // 取余数是9
            int pop = num % 10;
            // 返回数
            resNum = resNum * 10 + pop;
            num = num / 10;
        }
        return resNum;
    }


}
