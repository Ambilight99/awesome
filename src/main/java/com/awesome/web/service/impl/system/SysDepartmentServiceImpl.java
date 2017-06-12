package com.awesome.web.service.impl.system;

import com.awesome.web.domain.system.BaseZTree;
import com.awesome.web.domain.system.SysDepartment;
import com.awesome.web.mapper.system.SysDepartmentMapper;
import com.awesome.web.service.system.SysDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author adam
 * @ClassName SysDepartmentServiceImpl
 * @Description 系统部门ServiceImpl
 * @create 2017/6/11 11:36
 */
@Service
public class SysDepartmentServiceImpl implements SysDepartmentService {
    @Autowired
    private SysDepartmentMapper sysDepartmentMapper;

    /**
     * 部门树形结构
     *
     * @return
     */
    @Override
    public List<BaseZTree> treeData() {
        List<SysDepartment> departments = sysDepartmentMapper.list();
        List<BaseZTree> ztree = new ArrayList<>();
        departments.forEach(x->{
            ztree.add(BaseZTree.convert( x ));
        });
        return BaseZTree.treeModel(ztree,0L,2);
    }

    /**
     * 部门更新或者保存
     *
     * @param department
     * @return
     */
    @Override
    public int saveOrUpdate(SysDepartment department) {
        if(department.getId()==null){
            return sysDepartmentMapper.insert(department);
        }else{
            return sysDepartmentMapper.update(department);
        }
    }
}
