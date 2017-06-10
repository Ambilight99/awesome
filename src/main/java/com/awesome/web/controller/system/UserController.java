package com.awesome.web.controller.system;

import com.awesome.web.domain.common.datatable.DataTablePage;
import com.awesome.web.domain.system.SysUser;
import com.awesome.web.service.system.SysUserService;
import com.github.pagehelper.PageHelper;
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
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("list")
    public String list(){
        return "system/user_list";
    }

    @RequestMapping("add")
    public String add(){
        return "system/user_add";
    }

    @RequestMapping("/loadData")
    @ResponseBody
    public DataTablePage loadData(){
        PageHelper.startPage(1,20);
        List<SysUser> userList = sysUserService.list();
        return new DataTablePage(userList);
    }

    @RequestMapping("/loadData2")
    @ResponseBody
    public Map loadData2(){
        Map map = new HashMap();
        List list = new ArrayList();
        for(int i=0;i<10;i++){
            Map m = new HashMap();
            m.put("id",i);
            m.put("username","url"+i);
            m.put("createDate","title");
            list.add(m);
        }
        map.put("data",list);
        map.put("recordsTotal",11);
        map.put("recordsFiltered",11);
        return map;
    }
}
