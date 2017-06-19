package com.awesome.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author adam
 * @ClassName LoginController
 * @Description 登录控制器
 * @create 2017/6/4 10:03
 */
@Controller
public class LoginController {
    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = {"login"} , method = RequestMethod.GET )
    public String login(Map map, String error,String username){
        logger.info("跳转到登录页面!");
        map.put("error",error);
        map.put("username",username);
        return "login";
    }

    @RequestMapping("home")
    public String home(){
        return "home";
    }

    @RequestMapping(value = {"/","","index"} )
    public String index(Map map, HttpServletRequest request){
        return "index";
    }

}
