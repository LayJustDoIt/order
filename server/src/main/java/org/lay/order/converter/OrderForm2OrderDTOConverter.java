package org.lay.order.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.lay.order.dataobject.OrderDetail;
import org.lay.order.dto.OrderDTO;
import org.lay.order.enums.ResultExceptionEnum;
import org.lay.order.exception.OrderException;
import org.lay.order.form.OrderForm;
import java.util.ArrayList;
import java.util.List;

/**
 * orderForm 转 orderDTO
 * Create by Lay
 * 2018-01-04 22:01
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (Exception e) {
            log.error("【对象转换】错误, String = {}", orderForm.getItems());
            throw new OrderException(ResultExceptionEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
