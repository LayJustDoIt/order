package org.lay.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;

/**
 * Create by Lay
 * 2018-06-18 18:40
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
@Slf4j
public class HystrixControllerTest {


    @HystrixCommand(commandProperties = {
        @HystrixProperty(name = "", value = "")
    })
    @GetMapping(value = "/getProductListTest")
    public String getProductListTest() {

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject("http://localhost:9081/product/listForOrder",
                Arrays.asList("123456"), String.class);
        log.info("result -----> {}", result);
        return result;
    }

    private String defaultFallback() {
        return "默认提示哈哈哈哈哈：：：：系统异常， 请稍后再试~~~";
    }

}
