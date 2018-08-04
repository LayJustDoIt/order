/*
package org.lay.order.controller;

import org.lay.order.dto.OrderDTO;
import org.lay.order.message.StreamClient;
import org.lay.order.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

*/
/**
 * Create by Lay
 * 2018-05-01 12:58
 *//*

@RestController
@RequestMapping(value = "/send")
public class StreamMessageController {


    @Autowired
    private StreamClient streamClient;

    */
    /**
     * 发送消息
     *//*

    @GetMapping(value = "/streamMessage")
    public void sendMessage() {
        streamClient.output().send(
                MessageBuilder.withPayload("now : " + new Date()).build()
        );
    }

    */
/**
     * 发送消息 涉及到对象
     *//*

    @GetMapping(value = "/messageOfObj")
    public void sendMessageObj() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(KeyUtil.genUniqueKey());
        streamClient.output().send(
                MessageBuilder.withPayload(orderDTO).build()
        );
    }
}
*/
