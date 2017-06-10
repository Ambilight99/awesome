package com.awesome.web.controller.system;

import com.awesome.web.domain.common.ResultMessage;
import com.awesome.web.domain.common.datatable.DataTablePage;
import com.awesome.web.domain.common.datatable.DataTableSearch;
import com.awesome.web.domain.system.SysRole;
import com.awesome.web.service.system.SysRoleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author adam
 * @ClassName RoleContorller
 * @Description 系统角色控制器
 * @create 2017/6/10 11:44
 */
@Controller
@RequestMapping("system/role")
public class RoleContorller {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 角色展示
     * @return
     */
    @RequestMapping("list")
    public String list(){
        return "system/role_list";
    }

    /**
     * 角色新增
     * @return
     */
    @RequestMapping("add")
    public String add(Map map){
        SysRole role = new SysRole();
        role.setType(SysRole.CUSTOM);
        map.put("role",role);
        return "system/role_form";
    }

    /**
     * 角色编辑
     * @return
     */
    @RequestMapping("edit")
    public String edit(Map map, Long id){
        SysRole role = sysRoleService.findById(id);
        map.put("role",role);
        return "system/role_form";
    }

    /**
     * 角色关联资源页面
     * @return
     */
    @RequestMapping("resource/list")
    public String resourceList(Long id){
        return "system/role_resource_list";
    }

    @RequestMapping("loadData")
    @ResponseBody
    public DataTablePage loadData(SysRole role , DataTableSearch search){
        PageHelper.offsetPage(search.getStart(),search.getLength());
        List<SysRole> roleList = sysRoleService.list(role);
        return new DataTablePage(roleList);
    }

    /**
     * 保存或者更新
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public ResultMessage save(SysRole role){
        try {
            sysRoleService.saveOrUpdate(role);
            return ResultMessage.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultMessage.fail("操作失败！");
        }
    }

    /**
     * 保存或者更新
     * @return
     */
    @RequestMapping("resource/save")
    @ResponseBody
    public ResultMessage resourceSave(Long roleId, @RequestParam("resourceIds[]") Long[] resourceIds ){
        try {
            sysRoleService.resourceSave(roleId,resourceIds);
            return ResultMessage.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultMessage.fail("操作失败！");
        }
    }

}
