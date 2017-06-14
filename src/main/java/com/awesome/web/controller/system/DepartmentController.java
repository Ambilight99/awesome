package com.awesome.web.controller.system;

import com.awesome.web.domain.common.ResultMessage;
import com.awesome.web.domain.system.BaseZTree;
import com.awesome.web.domain.system.SysDepartment;
import com.awesome.web.service.system.SysDepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author adam
 * @ClassName DepartmentController
 * @Description 系统部门控制器
 * @create 2017/6/11 11:23
 */
@RequestMapping("system/department")
@Controller
public class DepartmentController {
    private final static Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private SysDepartmentService sysDepartmentService;

    @RequestMapping("add")
    public String add(Map map, Long id){
        SysDepartment department = new SysDepartment();
        department.setParent(id);
        department.setStatus(1);
        department.setOrder(1);
        map.put("department",department);
        return "system/department_form";
    }

    /**
     * 获取模型的树形结构
     * @return
     */
    @RequestMapping("treeData")
    @ResponseBody
    public List<BaseZTree> treeData(){
        List<BaseZTree> list = sysDepartmentService.treeData();
        return list;
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultMessage save(SysDepartment department){
        try {
            sysDepartmentService.saveOrUpdate(department);
            return ResultMessage.success("保存成功！",department);
        }catch (Exception e){
            logger.error("【部门】保存或者更新错误！",e);
            return ResultMessage.fail("保存失败！",department);
        }

    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultMessage delete(Long id){
        try {
            sysDepartmentService.delete(id);
            return ResultMessage.success("删除成功！");
        }catch (Exception e){
            logger.error("【部门】删除错误！",e);
            return ResultMessage.fail("删除失败！");
        }

    }
}
