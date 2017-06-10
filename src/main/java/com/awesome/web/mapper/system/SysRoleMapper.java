package com.awesome.web.mapper.system;

import com.awesome.web.domain.system.SysRole;
import com.awesome.web.domain.system.SysRoleResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author adam
 * @ClassName SysRoleMapper
 * @Description 系统角色Mapper
 * @create 2017/6/4 14:21
 */
@Repository
@Mapper
public interface SysRoleMapper {

    @Select(" select * from sys_role ")
    List<SysRole> listAll();

    /**
     * 根据角色里面的搜索条件，查询所有相关信息
     * @param role
     * @return
     */
    List<SysRole> listByRole(SysRole role);

    /**
     * 根据id查询角色
     * @param id
     * @return
     */
    @Select(" select * from sys_role where id=#{id} ")
    SysRole findById(Long id);

    /**
     * 保存
     * @param role
     * @return
     */
    int insert(SysRole role);

    /**
     * 更新
     * @param role
     * @return
     */
    int update(SysRole role);

    /**
     * 删除角色资源关系
     * @param roleId
     * @return
     */
    int deleteRoleResourceById(Long roleId);

    /**
     * 批量添加角色资源关系
     * @param list
     * @return
     */
    int insertRoleResourceBatch(List<SysRoleResource> list);
}
