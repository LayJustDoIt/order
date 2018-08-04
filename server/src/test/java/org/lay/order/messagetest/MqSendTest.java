package org.lay.order.messagetest;

import org.junit.Test;
import org.lay.order.OrderApplicationTests;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Create by Lay
 * 2018-03-25 11:07
 */
@Component
public class MqSendTest extends OrderApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void sendMessage() {
        amqpTemplate.convertAndSend("myQueue1", "nowTime: " + new Date());
    }


    @Test
    public void sendMessageGroup() {
        amqpTemplate.convertAndSend("myOrder", "computer", "我发送的是电脑的消息。");
    }

    @Test
    public void sendMessageGroupTwo() {
        amqpTemplate.convertAndSend("myOrder", "fruit", "我发送的是水果消息。");
    }

}
