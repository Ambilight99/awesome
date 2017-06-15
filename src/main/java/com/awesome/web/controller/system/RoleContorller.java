package com.awesome.web.controller.system;

import com.awesome.security.MyInvocationSecurityMetadataSourceService;
import com.awesome.web.domain.common.ResultMessage;
import com.awesome.web.domain.common.datatable.DataTablePage;
import com.awesome.web.domain.common.datatable.DataTableSearch;
import com.awesome.web.domain.system.SysRole;
import com.awesome.web.service.system.SysRoleService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author adam
 * @ClassName RoleContorller
 * @Description 系统角色控制器
 * @create 2017/6/10 11:44
 */
@Controller
@RequestMapping("system/role")
public class RoleContorller {
    private final static Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    private MyInvocationSecurityMetadataSourceService myInvocationSecurityMetadataSourceService;
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
        role.setStatus(1);
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
        List<SysRole> roleList = sysRoleService.list(search);
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
            return ResultMessage.success("保存成功！");
        }catch (Exception e){
            logger.error("【角色】保存或者更新错误！",e);
            return ResultMessage.fail("保存失败！");
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
            myInvocationSecurityMetadataSourceService.reloadResourceDefine(); //更新角色资源关系后，重新加载security元数据
            return ResultMessage.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultMessage.fail("操作失败！");
        }
    }

    /**
     * 获取关联用户数量
     * @param id
     * @return
     */
    @RequestMapping("user/count")
    @ResponseBody
    public ResultMessage userCount(Long id){
        try {
            int count = sysRoleService.userCount(id);
            return ResultMessage.success("获取关联用户数量成功！",count);
        }catch (Exception e){
            logger.error("【角色】获取关联用户数量错误！",e);
            return ResultMessage.fail("获取关联用户数量失败！");
        }
    }

    /**
     * 角色删除
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultMessage delete(Long id){
        try {
            SysRole role = sysRoleService.findById(id);
            if(role == null){
                return ResultMessage.fail("角色已被删除，请勿重复操作！");
            }
            if(Objects.equals(SysRole.SYSTEM, role.getType())){
                return ResultMessage.fail("系统角色，不能被删除！");
            }
            sysRoleService.delete(id);
            return ResultMessage.success("删除成功！");
        }catch (Exception e){
            logger.error("【角色】删除错误！",e);
            return ResultMessage.fail("删除失败！");
        }
    }

}
