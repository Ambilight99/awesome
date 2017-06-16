package com.awesome.web.controller.system;

import com.awesome.web.domain.common.ResultMessage;
import com.awesome.web.domain.common.datatable.DataTablePage;
import com.awesome.web.domain.common.datatable.DataTableSearch;
import com.awesome.web.domain.system.SysRole;
import com.awesome.web.domain.system.SysUser;
import com.awesome.web.service.system.SysRoleService;
import com.awesome.web.service.system.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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
    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("list")
    public String list(){
        return "system/user_list";
    }

    @RequestMapping("add")
    public String add(Map map, Long department,String departmentName){
        SysUser user = new SysUser();
        user.setDepartment(department);
        user.setDepartmentName(departmentName);
        user.setStatus(1);
        map.put("user",user);
        return "system/user_form";
    }

    @RequestMapping("edit")
    public String edit(Map map, Long id){
        SysUser user = sysUserService.findById(id);
        map.put("user", user);
        return "system/user_form";
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultMessage delete(Long id){
        try {
            sysUserService.deleteById(id);
            return ResultMessage.success("删除成功！");
        }catch (Exception e){
            logger.error("【用户】删除错误！",e);
            return ResultMessage.fail("删除失败！");
        }
    }


    /**
     * 加载人员信息
     * @param user
     * @param search datatable查询条件
     * @param subdivision 查询时，是否关联子部门查询
     * @return
     */
    @RequestMapping("/loadData")
    @ResponseBody
    public DataTablePage loadData(SysUser user , DataTableSearch search ,
           @RequestParam(value = "subdivision",defaultValue = "false" ) boolean subdivision){
        user.setStatus(1); //状态为有效的用户
        List<SysUser> userList = new ArrayList<>();
        if(user.getDepartment()==null){
            userList = sysUserService.list(user,search);
        }else{
            userList = sysUserService.list(user,search,true);
        }
        return new DataTablePage(userList);
    }

    @RequestMapping("/save")
    @ResponseBody
    public ResultMessage save(SysUser user){
        try {
            sysUserService.saveOrUpdate(user);
            return ResultMessage.success("保存成功！");
        }catch (Exception e){
            logger.error("【用户】保存或者更新错误！",e);
            return ResultMessage.fail("保存失败！");
        }
    }

    /**
     * 获取角色信息
     * @param map
     * @param id 用户id
     * @return
     */
    @RequestMapping("auth/list")
    @ResponseBody
    public List<SysRole> authList(Map map, Long id){
        List<SysRole> roles = sysRoleService.authorizationByUserId(id);
        return roles;
    }

    /**
     * 用户-角色关系保存
     * @param roles
     * @param userId
     * @return
     */
    @RequestMapping("role/save")
    @ResponseBody
    public ResultMessage roleSave(@RequestParam(value = "roles[]") Long[] roles , Long userId){
        try {
            sysUserService.roleSave(roles,userId);
            return ResultMessage.success("保存成功！");
        }catch (Exception e){
            logger.error("【用户-角色关系】保存或者更新错误！",e);
            return ResultMessage.fail("保存失败！");
        }
    }
}
