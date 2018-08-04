package org.lay.order.controller;

import org.lay.product.client.ProductClient;
import org.lay.product.common.DecreaseStockInput;
import org.lay.product.common.ProductInfoOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

/**
 * Create by Lay
 * 2018-03-15 0:41
 */
@RestController
public class ClientController {

    @Autowired
    private ProductClient productClient;

    @GetMapping("/getProductList")
    public String getProductList() {
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(Arrays.asList("123456", "654321"));
        System.out.println("The result is : " + productInfoList);
        return "ok";
    }

    @GetMapping("/decreaseStock")
    public String decreaseStock() {
        productClient.decreaseStock(Arrays.asList(
                new DecreaseStockInput("654321", 1)
        ));
        return "ok";
    }

}
