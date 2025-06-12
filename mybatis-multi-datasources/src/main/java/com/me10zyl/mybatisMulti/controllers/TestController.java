package com.me10zyl.mybatisMulti.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping
    public String test() {
        return "Hello World";
    }
}
