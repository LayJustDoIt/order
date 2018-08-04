package org.lay.order.utils;

import java.util.Random;

/**
 * Create by Lay
 * 2018-01-02 21:13
 */
public class KeyUtil {

    /**
     * 生成策略：系统毫秒 + 6位随机数
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer randomNum = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(randomNum);
    }
}
