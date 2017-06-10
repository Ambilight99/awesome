package com.awesome.web.controller.system;

import com.awesome.web.domain.common.datatable.DataTablePage;
import com.awesome.web.domain.common.datatable.DataTableSearch;
import com.awesome.web.domain.system.SysResource;
import com.awesome.web.service.system.SysResourceService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author adam
 * @ClassName ResourceController
 * @Description 资源控制器
 * @create 2017/6/8 18:16
 */
@Controller
@RequestMapping("system/resource")
public class ResourceController {

    @Autowired
    public SysResourceService sysResourceService;

    @RequestMapping("list")
    public String list(){
        return "system/resource_list";
    }

    @RequestMapping("/loadData")
    @ResponseBody
    public DataTablePage loadData(SysResource resource , DataTableSearch search){
        PageHelper.offsetPage(search.getStart(),search.getLength());
        List<SysResource> resourceList = sysResourceService.list(resource);
        return new DataTablePage(resourceList);
    }
}
