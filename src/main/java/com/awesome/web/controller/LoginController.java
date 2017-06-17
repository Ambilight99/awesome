package com.awesome.web.controller;

import com.awesome.web.domain.system.BaseZTree;
import com.awesome.web.domain.system.SysResource;
import com.awesome.web.domain.system.SysUser;
import com.awesome.web.service.system.SysModelService;
import com.awesome.web.service.system.SysResourceService;
import com.awesome.web.service.system.SysUserService;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysModelService sysModelService;

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
        //根据用户名获取用户显示的模块
        SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<BaseZTree> menus = new ArrayList<>();
        if(user!=null || user.getId()!=null){
            menus =sysModelService.listByUserId(user.getId());
        }
        request.getSession().setAttribute("menus",menus); //将标签放入session
        return "index";
    }

}
