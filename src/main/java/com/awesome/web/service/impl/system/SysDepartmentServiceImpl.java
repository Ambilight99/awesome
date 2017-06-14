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
            BaseZTree z = BaseZTree.convert( x );
            if( x.getLevel()<=2){
                z.setOpen(true);
            }else{
                z.setOpen(false);
            }
            ztree.add(z);
        });
        return ztree;//BaseZTree.treeModel(ztree,0L,2);
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

    /**
     * 删除部门
     *
     * @param id
     * @return
     */
    @Override
    public int delete(Long id) {
        if (id==null){
            return 0;
        }
        return sysDepartmentMapper.delete(id);
    }
}
