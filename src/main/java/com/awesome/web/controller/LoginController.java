package com.awesome.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author adam
 * @ClassName LoginController
 * @Description 登录控制器
 * @create 2017/6/4 10:03
 */
@Controller
public class LoginController {

    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @RequestMapping("home")
    public String home(){
        return "home";
    }

    @RequestMapping("hello")
    public String hello(){
        return "hello";
    }
}
