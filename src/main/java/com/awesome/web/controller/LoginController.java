package com.awesome.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author adam
 * @ClassName LoginController
 * @Description 登录控制器
 * @create 2017/6/4 10:03
 */
@Controller
public class LoginController {

    @RequestMapping(value = "login" , method = RequestMethod.GET )
    public String login(){
        System.out.println(21);
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

    @RequestMapping("freemarker")
    public String freemarker() {
        return "freemarker";
    }
}
