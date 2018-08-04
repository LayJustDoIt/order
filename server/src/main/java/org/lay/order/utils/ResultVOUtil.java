package org.lay.order.utils;

import org.lay.order.VO.ResultVO;
import org.lay.order.enums.BuyerProductEnum;


/**
 * Create by Lay
 * 2017-12-31 13:10
 */
public class ResultVOUtil {

    /**
     * 成功时： 有参
     * @param obj
     * @return
     */
    public static ResultVO success(Object obj) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(BuyerProductEnum.SUCCESS.getCode());
        resultVO.setMsg(BuyerProductEnum.SUCCESS.getMessage());
        resultVO.setData(obj);
        return resultVO;
    }

    /**
     * 成功时： 无参
     * @return
     */
    public static ResultVO success() {
        return success(null);
    }

    /**
     * 失败
     * @param code
     * @param message
     * @return
     */
    public static ResultVO error(Integer code, String message) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(message);
        return resultVO;
    }
}
