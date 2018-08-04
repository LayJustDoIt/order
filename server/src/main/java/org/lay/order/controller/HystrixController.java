package org.lay.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;

/**
 * SpringCloud Hystrix Controller
 * Create by Lay
 * 2018-04-01 17:54
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {


    // 超时设置
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
//    })
    // 熔断设置
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
    })
    @GetMapping(value = "/getProductInfoList")
    public String getProductInfoList(@RequestParam Integer number) {
        if (number % 2 == 0) {
            return "success";
        }
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://localhost:9081/product/listForOrder",
                Arrays.asList("123456"), String.class);
    }

    private String fallback() {
        return "太特么挤了， 一会儿再来吧。。。";
    }

    private String defaultFallback() {
        return "默认提示：太特么挤了， 一会儿再来吧。。。";
    }

}
