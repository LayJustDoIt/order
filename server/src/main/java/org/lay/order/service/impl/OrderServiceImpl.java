package org.lay.order.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.lay.order.dataobject.OrderDetail;
import org.lay.order.dataobject.OrderMaster;
import org.lay.order.dto.OrderDTO;
import org.lay.order.enums.OrderStatusEnum;
import org.lay.order.enums.PayStatusEnum;
import org.lay.order.enums.ResultExceptionEnum;
import org.lay.order.exception.OrderException;
import org.lay.order.repository.OrderDetailRepository;
import org.lay.order.repository.OrderMasterRepository;
import org.lay.order.service.OrderService;
import org.lay.order.utils.KeyUtil;
import org.lay.product.client.ProductClient;
import org.lay.product.common.DecreaseStockInput;
import org.lay.product.common.ProductInfoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Create by Lay
 * 2018-01-02 17:07
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductClient productClient;

    /**
     * 创建订单
     *
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        // 生成订单id
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        List<String> productIdList = orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getProductId).collect(Collectors.toList());
        // 查询商品信息（调用商品服务）
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(productIdList);
        // 计算总价
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for (ProductInfoOutput productInfo : productInfoList) {
                // 商品id是否等于订单详情中的商品id
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    orderAmount = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
                    // 补全orderDetail内容
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    // 详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }
        // 扣库存（调用商品服务）
        List<DecreaseStockInput> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(cartDTOList);
        // 订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }

    /**
     * 完结订单
     * @param orderId
     * @return
     */
    @Override
    @Transactional
    public OrderDTO finish(String orderId) {
        /**1. 查询订单  Spring Data Jpa 2.x版本的写法 */
        Optional<OrderMaster> optional = orderMasterRepository.findById(orderId);
        if (!optional.isPresent()) {
            throw new OrderException(ResultExceptionEnum.ORDER_NOT_EXIST);
        }
        // 获取当前对象
        OrderMaster orderMaster = optional.get();
        /**2. 订单状态  */
        if (!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new OrderException(ResultExceptionEnum.ORDER_STATUS_ERROR);
        }
        /**3. 修改订单状态 */
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderMasterRepository.save(orderMaster);
        /**4. 查询订单详情 */
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (StringUtils.isEmpty(orderDetailList)) {
            throw new OrderException(ResultExceptionEnum.ORDER_DETAIL_EMPTY);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

}
