package org.lay.order.message;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.lay.order.utils.JsonUtil;
import org.lay.product.common.ProductInfoOutput;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Create by Lay
 * 2018-03-25 18:59
 */
@Component
@Slf4j
public class ProductInfoReceiver {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message) {
        // message => productInfoOutput
        List<ProductInfoOutput> productInfoOutputList = (List<ProductInfoOutput>) JsonUtil.fromJson(
                message, new TypeReference<List<ProductInfoOutput>>(){});
        log.info("从队列【{}】接收消息：{}", "productInfo", productInfoOutputList);
        for (ProductInfoOutput productInfoOutput : productInfoOutputList) {
            // 写入redis(单个) 草他妈比 redis依赖不进来， 草
            redisTemplate.opsForValue().set(
                    String.format(PRODUCT_STOCK_TEMPLATE, productInfoOutput.getProductId()),
                    productInfoOutput.getProductStock() + "");
        }
    }

}
