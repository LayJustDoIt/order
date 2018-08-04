package org.lay.order.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * 订单对象
 * Create by Lay
 * 2018-01-04 21:34
 */
@Data
public class OrderForm {

    /**
     * 用户姓名
     */
    @NotEmpty(message = "用户名必填")
    private String name;

    /**
     * 用户电话
     */
    @NotEmpty(message = "电话必填")
    private String phone;

    /**
     * 用户地址
     */
    @NotEmpty(message = "地址必填")
    private String address;

    /**
     * 用户微信id
     */
    @NotEmpty(message = "openid必填")
    private String openid;

    /**
     * 用户购物车
     */
    @NotEmpty(message = "购物车信息必填")
    private String items;
}
