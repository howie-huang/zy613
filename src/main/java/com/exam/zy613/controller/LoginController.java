package com.exam.zy613.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.exam.zy613.entity.User;
import com.exam.zy613.service.UserService;
import com.exam.zy613.util.BaseUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登陆
 * @author 31515
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService service;
    @RequestMapping("/login")
    public String login(@RequestBody User user, Model model) {
        System.out.println(user);
        Subject subject = SecurityUtils.getSubject();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(), user.getPassword());
            subject.login(token);
        } catch (UnknownAccountException uae) {
            model.addAttribute("message","用户名不存在");
            return "login";
        } catch (IncorrectCredentialsException ice) {
            model.addAttribute("message","密码错误");
            return "login";
        } catch (LockedAccountException lae) {
            model.addAttribute("message","用户被锁定");
            return "login";
        } catch (ExcessiveAttemptsException eae) {
            model.addAttribute("message","认证超出次数");
            return "login";
        } catch (Exception e) {
            model.addAttribute("message","发生未知异常，登陆失败");
            return "login";
        }
        Wrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("login_name", user.getLoginName());
        User returnUser = service.selectOne(wrapper);
        model.addAttribute("loginUser",returnUser.getUserName());
        return "hello";
    }

//    public static void main(String[] args) {
//        System.out.println(BaseUtil.tranWord("admin","admin"));
//    }
}
