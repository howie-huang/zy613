package com.exam.zy613.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * thymeleaf路径跳转
 * @author 31515
 */
@Controller
public class ThymeleafPath {
    @RequestMapping("/dept/toShowDept")
    public String toHello(){
        return "dept/showDept";
    }
    @RequestMapping("/role/toShowRole")
    public String toUser(){
        return "role/showRole";
    }
    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }
    @RequestMapping("/loginOut")
    public String toOut(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
}
