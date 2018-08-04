package org.lay.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Order ----> client
 * Create by Lay
 * 2018-06-16 15:25
 */
@RestController
@Slf4j
public class ClientController1 {

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;


    @GetMapping(value = "/getProductMsg")
    public String getMsg() {
        // 1. 直接 实例化restTemplate 访问
//        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject("http://localhost:9081/msg", String.class);
//        log.info("return result : ---> {}", result);
//        return result;
        // 2. 使用LoadBalancedClient
        // 访问那个服务的id
        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance instance = loadBalancerClient.choose("PRODUCT");
        String url = String.format("http://%s:%s", instance.getHost(), instance.getPort()) + "/msg";
        String result = restTemplate.getForObject(url, String.class);
        log.info("result ----> {} ", result);
//        String result = restTemplate.getForObject("http://PRODUCT/msg", String.class);
//        log.info("result ----> {}", result);
        return result;
    }

}
