package org.lay.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.lay.order.VO.ResultVO;
import org.lay.order.converter.OrderForm2OrderDTOConverter;
import org.lay.order.dto.OrderDTO;
import org.lay.order.enums.ResultExceptionEnum;
import org.lay.order.exception.OrderException;
import org.lay.order.form.OrderForm;
import org.lay.order.service.OrderService;
import org.lay.order.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Create by Lay
 * 2018-01-04 20:36
 */
@RestController
@RequestMapping(value = "/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     */
    @PostMapping(value = "/create")
    public ResultVO<Map<String, String>> createOrder(@Valid OrderForm orderForm,
                                                     BindingResult bindingResult) {
        // 判断校验是否出错
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new OrderException(ResultExceptionEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        // orderForm 转换 orderDTO
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        // 创建订单前判断购物车是否为空
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new OrderException(ResultExceptionEnum.CART_EMPTY);
        }
        // 调用Service创建订单
        OrderDTO createResult = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<String, String>();
        map.put("orderId", createResult.getOrderId());
        return ResultVOUtil.success(map);
    }

    /**
     * 完结订单
     * @param orderId
     * @return
     */
    @PostMapping(value = "/finish")
    public ResultVO<OrderDTO> orderFinished(@RequestParam String orderId) {
        return ResultVOUtil.success(orderService.finish(orderId));
    }

}
