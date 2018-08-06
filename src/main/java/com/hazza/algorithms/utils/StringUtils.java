package com.hazza.algorithms.utils;

import java.util.Random;

/**
 * @description:
 * @user: HazzaCheng
 * @contact: hazzacheng@gmail.com
 * @date: 18-8-6
 * Change log:
 */
public class StringUtils {
    /**
     * Generate random string.
     * @param length The length of String.
     * @return Random string.
     */
    public static String getRandomString(int length){
        String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        Random random=new Random();

        StringBuffer sb=new StringBuffer();
        for(int i = 0; i < length; ++i){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }

        return sb.toString();
    }
}
