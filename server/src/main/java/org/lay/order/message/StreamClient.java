package org.lay.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Stream Message
 * Create by Lay
 * 2018-05-01 12:47
 */
public interface StreamClient {
    String INPUT = "myMessage";

    String INPUT2 = "myMessage2";

    @Input(StreamClient.INPUT) // 这个不能重复定义
    SubscribableChannel input();

    @Output(StreamClient.INPUT2)
    MessageChannel output();

//    @Input(StreamClient.INPUT2)
//    SubscribableChannel input2();
//
//    @Output(StreamClient.INPUT2)
//    MessageChannel output2();

}
