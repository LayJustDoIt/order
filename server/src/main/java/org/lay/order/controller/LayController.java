package org.lay.order.controller;


import org.lay.order.config.LayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by Lay
 * 2018-04-29 16:03
 */
@RestController
@RequestMapping(value = "/lay")
public class LayController {

    @Autowired
    private LayConfig layConfig;

    @GetMapping(value = "/print")
    public String layPrint() {
        return "name : " + layConfig.getName() + ", age : " + layConfig.getAge();
    }
}
