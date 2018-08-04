package org.lay.order.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RabbitMQ SendMessage
 * Create by Lay
 * 2018-05-01 8:42
 */
@RestController
@RequestMapping(value = "/send")
public class RabbitMQController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping(value = "/message")
    public String sendMessage() {
        amqpTemplate.convertAndSend("myQueue", "通过Controller发送。。。");
        return "发送成功。";
    }

}
