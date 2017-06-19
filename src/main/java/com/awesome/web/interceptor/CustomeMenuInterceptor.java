package com.awesome.web.interceptor;

import com.awesome.web.domain.system.BaseZTree;
import com.awesome.web.domain.system.SysUser;
import com.awesome.web.service.system.SysModelService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author adam
 * @ClassName CustomeMenuInterceptor
 * @Description 自定义菜单拦截器
 * @create 2017/6/19 16:10
 */
@Aspect
@Component
public class CustomeMenuInterceptor {
    @Autowired
    private SysModelService sysModelService;

    //匹配跳转页面（Controller包中，包含@RequestMapping 但不包含@ResponseBody的方法 ）
    @Pointcut("execution(* com.awesome.web.controller..*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)" +
            " && !@annotation(org.springframework.web.bind.annotation.ResponseBody) ")
    public void controllerMethodPointcut(){}



    @After("controllerMethodPointcut()")
    public void addMenuToSession(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)(RequestContextHolder.getRequestAttributes());
        HttpServletRequest request = servletRequestAttributes.getRequest();
        if(request!=null){
            HttpSession session =request.getSession();
            if(session.getAttribute("menus")==null){    //如果session中不存在menus，则添加menus
                //根据用户名获取用户显示的模块
                Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                if(obj instanceof SysUser){
                    SysUser user = (SysUser) obj;
                    List<BaseZTree> menus = new ArrayList<>();
                    if(user!=null || user.getId()!=null){
                        menus =sysModelService.listByUserId(user.getId());
                    }
                    session.setAttribute("menus",menus);
                }
            }

        }
    }

}
