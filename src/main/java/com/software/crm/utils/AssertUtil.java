package com.software.crm.utils;


import com.software.crm.exceptions.ParamsException;

/**
 * 做判断时使用
 */
public class AssertUtil {

    public static void isTrue(Boolean flag, String msg) {
        if (flag) {
            throw new ParamsException(msg);
        }
    }

}
