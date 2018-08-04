package org.lay.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.lay.order.client.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by Lay
 * 2018-06-17 15:35
 */
@RestController
@Slf4j
public class ClientControllerTest {

    @Autowired
    private ProductClient productClient;

    @GetMapping(value = "/getMsg")
    public String msgTest() {
        /** 使用feign调用product的msg接口 */
        String result = productClient.getProduct();
        log.info("result ----> {}", result);
        return result;
    }

}
