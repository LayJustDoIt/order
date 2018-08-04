
package org.lay.order.message;

import lombok.extern.slf4j.Slf4j;
import org.lay.order.dto.OrderDTO;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;


/**
 * Create by Lay
 * 2018-05-01 12:55
 */

@Slf4j
@Component
@EnableBinding(StreamClient.class)
public class StreamReceiver {


    /**
     * 接收消息并打印
     */
//    @StreamListener(StreamClient.INPUT)
//    public void process(Object message) {
//        log.info("StreamReceiver : " + message);
//    }

    /**
     * 接收消息  对象
     *
     * @param orderDTO
     */
    @StreamListener(StreamClient.INPUT)
    @SendTo(value = StreamClient.INPUT2)
    public String processObj(OrderDTO orderDTO) {
        log.info("StreamReceiverObject : " + orderDTO);
        return "received.";
    }

    @StreamListener(StreamClient.INPUT2)
    public void process(String message) {
        log.info("StreamReceiver2 : " + message);
    }

}
