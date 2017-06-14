package com.awesome.web.service.system;

import com.awesome.web.domain.system.BaseZTree;
import com.awesome.web.domain.system.SysDepartment;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author adam
 * @ClassName SysDepartmentService
 * @Description 系统部门Service
 * @create 2017/6/11 11:36
 */
@Transactional
public interface SysDepartmentService {

    /**
     * 部门树形结构
     * @return
     */
    List<BaseZTree> treeData();

    /**
     * 部门更新或者保存
     * @param department
     * @return
     */
    int saveOrUpdate(SysDepartment department);

    /**
     * 删除部门
     * @param id
     * @return
     */
    int delete(Long id);
}
