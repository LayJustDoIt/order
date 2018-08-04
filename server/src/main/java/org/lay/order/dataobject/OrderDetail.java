package org.lay.order.dataobject;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Create by Lay
 * 2018-03-14 15:11
 */
@Data
@Entity
public class OrderDetail {
    /** 订单详情Id. */
    @Id
    private String detailId;

    /** 订单id. */
    private String orderId;

    /** 商品id. */
    private String productId;

    /** 商品名称. */
    private String productName;

    /** 当前价格,单位分. */
    private BigDecimal productPrice;

    /** 数量. */
    private Integer productQuantity;

    /** 小图. */
    private String productIcon;
}
