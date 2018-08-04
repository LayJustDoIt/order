package org.lay.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * 接收MQ消息
 * Create by Lay
 * 2018-03-25 10:52
 */
@Slf4j
@Component
public class MqReceiver {

    /**
     * 自动声明队列
     * @param message
     */
    @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    public void receiveMessage(String message) {
        log.info(message);
    }

    /**
     * queue和exchange绑定
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("computerOrder"),
            key = "computer",
            exchange = @Exchange("myOrder")
    ))
    public void processComputer(String message) {
        log.info("computer ReceiverMessage : " + message);
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("fruitOrder"),
            key = "fruit",
            exchange = @Exchange("myOrder")
    ))
    public void processFruit(String message) {
        log.info("order ReceiverMessage : " + message);
    }
}
