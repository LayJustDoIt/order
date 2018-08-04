package org.lay.order.enums;

import lombok.Getter;

/**
 * Create by Lay
 * 2017-12-31 13:12
 */
@Getter
public enum BuyerProductEnum {

    SUCCESS(0, "成功"),
    ERROR(1, "失败")
    ;

    private Integer code;

    private String message;

    BuyerProductEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
