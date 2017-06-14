package com.awesome.web.mapper.system;

import com.awesome.web.domain.system.SysDepartment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author adam
 * @ClassName SysDepartmentMapper
 * @Description 系统部门Mapper
 * @create 2017/6/11 11:37
 */
@Repository
@Mapper
public interface SysDepartmentMapper {
    /**
     * 列出所有部门
     * @return
     */
    @Select(" select * from sys_department order by `order` asc ")
    List<SysDepartment> list();

    /**
     * 保存
     * @param department
     * @return
     */
    int insert(SysDepartment department);

    /**
     * 更新
     * @param department
     * @return
     */
    int update(SysDepartment department);

    /**
     * 删除部门
     * @param id
     * @return
     */
    int delete(Long id);
}
