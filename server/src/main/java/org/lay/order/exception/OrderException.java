package org.lay.order.exception;

import lombok.Getter;
import org.lay.order.enums.ResultExceptionEnum;

/**
 * Create by Lay
 * 2018-01-02 19:44
 */
@Getter
public class OrderException extends RuntimeException {

    private Integer code;

    /**
     * 单引用构造
     * @param resultExceptionEnum
     */
    public OrderException(ResultExceptionEnum resultExceptionEnum) {
        super(resultExceptionEnum.getMessage());
        this.code = resultExceptionEnum.getCode();
    }

    /**
     * 双形参构造
     * @param code
     * @param message
     */
    public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
