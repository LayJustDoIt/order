package org.lay.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by Lay
 * 2018-04-28 21:17
 */
@RestController
@RefreshScope
@RequestMapping(value = "/myenv")
public class EnvController {

    @Value("${env}")
    private String env;

    @GetMapping(value = "/print")
    public String print() {
        return env;
    }

    @GetMapping(value = "/test")
    public Result test() {
        return new Result("test", 1);
    }
}
