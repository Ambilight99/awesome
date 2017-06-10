package com.awesome.web.controller.system;

import com.awesome.web.domain.common.ResultMessage;
import com.awesome.web.domain.system.ModelResourceTree;
import com.awesome.web.domain.system.SysModel;
import com.awesome.web.service.system.SysModelService;
import com.awesome.web.service.system.SysResourceService;
import com.awesome.web.service.system.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author adam
 * @ClassName ModelController
 * @Description 资源模块控制器
 * @create 2017/6/8 18:16
 */
@Controller
@RequestMapping("system/model")
public class ModelController {

    @Autowired
    public SysModelService sysModelService;
    @Autowired
    public SysResourceService sysResourceService;


    /**
     * 跳转到添加页面
     * @return
     */
    @RequestMapping("add")
    public String add( Map map, Long id){
        SysModel model = new SysModel();
        model.setParent(id);
        model.setAbbr("MODEL_");
        model.setOrder(1);
        map.put("model",model);
        return "/system/model_form";
    }

    /**
     * 跳转到编辑页面
     * @return
     */
    @RequestMapping("edit")
    public String edit(Map map , Long id){
        SysModel model = sysModelService.findById(id);
        map.put("model",model);
        return "/system/model_form";
    }

    /**
     * 获取模型的树形结构
     * @return
     */
    @RequestMapping("treeData")
    @ResponseBody
    public List<ModelResourceTree> treeData(){
        List<ModelResourceTree> list = sysModelService.treeData();
        return list;
    }

    /**
     * 获取模型和资源的树形结构
     * @return
     */
    @RequestMapping("resource/treeData")
    @ResponseBody
    public List<ModelResourceTree> resourceTreeData(Long roleId){
        List<ModelResourceTree> list = sysModelService.resourceTreeData(roleId);
        return list;
    }

    /**
     * 保存或更新 SysModel
     * @param model
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public ResultMessage save( SysModel model ){
        sysModelService.saveOrUpdate(model);
        return ResultMessage.success(model);
    }

    /**
     * 模块简称是否存在
     * @param abbr
     * @return
     */
    @RequestMapping("isAbbrExist")
    @ResponseBody
    public boolean isAbbrExist(String abbr){
        SysModel model = sysModelService.findByAbbr(abbr);
        return model==null?false:true;
    }
}
