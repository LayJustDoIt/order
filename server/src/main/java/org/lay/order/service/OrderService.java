package org.lay.order.service;

import org.lay.order.dto.OrderDTO;

/**
 * Create by Lay
 * 2018-01-02 17:00
 */
public interface OrderService {

    /** 创建订单 */
    OrderDTO create(OrderDTO orderDTO);

    /** 完结订单 (只能卖家操作)*/
    OrderDTO finish(String orderId);
}
