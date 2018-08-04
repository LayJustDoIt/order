package org.lay.order.utils;

/**
 * 数学工具类
 * Create by Lay
 * 2018-01-08 16:55
 */
public class MathUtil {

    /** 参考数值 */
    private static final Double Money_Range = 0.01;

    /**
     * 比较两个金额是否相等
     * @param d1
     * @param d2
     * @return
     */
    public static Boolean equals(Double d1, Double d2) {
        if (Math.abs(d1 - d2) < Money_Range) {
            return true;
        }
        return false;
    }

}
