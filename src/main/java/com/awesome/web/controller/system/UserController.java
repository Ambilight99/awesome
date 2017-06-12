package com.awesome.web.controller.system;

import com.awesome.web.domain.common.datatable.DataTablePage;
import com.awesome.web.domain.common.datatable.DataTableSearch;
import com.awesome.web.domain.system.SysUser;
import com.awesome.web.service.system.SysUserService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author adam
 * @ClassName UserController
 * @Description 系统用户控制器
 * @create 2017/6/7 18:50
 */
@Controller
@RequestMapping("system/user")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("list")
    public String list(){
        return "system/user_list";
    }

    @RequestMapping("add")
    public String add(Long department){
        SysUser user = new SysUser();
        user.setDepartment(department);
        user.setStatus(1);
        return "system/user_form";
    }

    @RequestMapping("edit")
    public String edit(Long id){

        return "system/user_form";
    }

    @RequestMapping("/loadData")
    @ResponseBody
    public DataTablePage loadData(SysUser user , DataTableSearch search){
        PageHelper.offsetPage(search.getStart(),search.getLength());
        List<SysUser> userList = sysUserService.list(user);
        return new DataTablePage(userList);
    }
}
