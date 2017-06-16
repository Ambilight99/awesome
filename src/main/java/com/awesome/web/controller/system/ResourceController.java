package com.awesome.web.controller.system;

import com.awesome.web.domain.common.ResultMessage;
import com.awesome.web.domain.common.datatable.DataTablePage;
import com.awesome.web.domain.common.datatable.DataTableSearch;
import com.awesome.web.domain.system.SysResource;
import com.awesome.web.service.system.SysResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author adam
 * @ClassName ResourceController
 * @Description 资源控制器
 * @create 2017/6/8 18:16
 */
@Controller
@RequestMapping("system/resource")
public class ResourceController {
    private final static Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    public SysResourceService sysResourceService;

    /**
     * 打开资源新增页面
     * @param map
     * @param model
     * @return
     */
    @RequestMapping("add")
    public String add(Map map, Long model){
        SysResource resource = new SysResource();
        resource.setType(SysResource.URL);
        resource.setModel(model);
        resource.setStatus(1);
        map.put("resource", resource);
        return "system/resource_form";
    }

    @RequestMapping("edit")
    public String edit(Map map, Long id){
        SysResource resource = sysResourceService.findById(id);
        map.put("resource",resource);
        return "system/resource_form";
    }

    /**
     * 资源列表页面
     * @return
     */
    @RequestMapping("list")
    public String list(){
        return "system/resource_list";
    }

    /**
     * 加载资源列表页面的数据
     * @param resource
     * @param search datatable查询条件
     * @param subdivision 查询时，是否关联子部门查询
     * @return
     */
    @RequestMapping("loadData")
    @ResponseBody
    public DataTablePage loadData(SysResource resource , DataTableSearch search
            , @RequestParam(value = "subdivision",defaultValue = "false" ) boolean subdivision){
        List<SysResource> resourceList;
        if(resource.getModel()==null){
            resourceList = sysResourceService.list(resource,search);
        }else{
            resourceList = sysResourceService.list(resource,search,true);
        }
        return new DataTablePage(resourceList);
    }

    /**
     * 保存或者更新一条资源
     * @param resource
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public ResultMessage save(SysResource resource){
        try {
            sysResourceService.saveOrUpdate(resource);
            return ResultMessage.success("保存成功！");
        }catch (Exception e){
            logger.error("【资源】保存或者更新错误！",e);
            return ResultMessage.fail("保存失败！");
        }
    }

    /**
     * 根据id删除一条资源，以及资源与角色的关联
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultMessage delete(Long id){
        try {
            if(id==null){
                return ResultMessage.fail("资源已被删除，请勿重复操作！");
            }
            sysResourceService.deleteById(id);
            return ResultMessage.success("删除成功！");
        }catch (Exception e){
            logger.error("【资源】删除错误！",e);
            return ResultMessage.fail("删除失败！");
        }
    }
}
