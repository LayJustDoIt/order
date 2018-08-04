package org.lay.order.dto;

import lombok.Data;

/**
 * 购物车
 * Create by Lay
 * 2018-01-02 21:46
 */
@Data
public class CartDTO {

    /** 商品id */
    private String productId;

    /** 商品数量 */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
