package com.exam.zy613.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * thymeleaf路径跳转
 * @author 31515
 */
@Controller
public class ThymeleafPath {
    @RequestMapping("/hello")
    public String toHello(){
        return "hello";
    }
    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }
}
