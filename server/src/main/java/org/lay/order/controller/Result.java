package org.lay.order.controller;

import lombok.Data;

/**
 * Create by Lay
 * 2018-04-29 11:58
 */
@Data
public class Result {

    private String name;

    private Integer age;

    public Result(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
