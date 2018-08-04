package org.lay.order;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@SpringCloudApplication
@EnableFeignClients(basePackages = {"org.lay.product.client", "org.lay.order.client"})
@ComponentScan(basePackages = "org.lay")
public class OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}
}
