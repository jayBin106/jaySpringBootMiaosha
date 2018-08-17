package com.jaymiaosha.util;

import java.util.UUID;

/**
 * Created by lenovo on 2018/7/31.
 */
public class UUidUtil {
    public static String get() {
        String string = UUID.randomUUID().toString();
        String replace = string.replace("-", "");
        return replace;
    }
}
