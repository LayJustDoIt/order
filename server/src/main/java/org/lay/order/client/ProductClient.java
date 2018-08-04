package org.lay.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Create by Lay
 * 2018-06-17 15:31
 */
@FeignClient(name = "product") // Service-Id
public interface ProductClient {

    @GetMapping(value = "/msg") // 对应的接口
    String getProduct();
}
