package com.ahui.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName $
 * @Author pengzhaohui
 * @Description
 * @Date $ $
 **/
@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Welcome to Store Application!";
    }
}