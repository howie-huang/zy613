package com.exam.zy613.controller;

import com.exam.zy613.entity.User;
import com.exam.zy613.service.UserService;
import com.exam.zy613.util.ResultData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public ResultData login(@RequestBody User user){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            return new ResultData(true,"已经登陆",null);
        }else {
            try {
                UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(),user.getPassword());
                subject.login(token);
            }catch (UnknownAccountException uae){
                return new ResultData(false,"用户名不存在",null);
            }catch (IncorrectCredentialsException ice){
                return new ResultData(false,"密码错误",null);
            }catch (LockedAccountException lae){
                return new ResultData(false,"用户被锁定",null);
            }catch (ExcessiveAttemptsException eae){
                return new ResultData(false,"认证超出次数",null);
            }catch (Exception e){
                return new ResultData(false,"发生未知异常，登陆失败",null);
            }
            return new ResultData(true,"登陆成功",null);
        }
    }
}
