package com.awesome.web.mapper.system;

import com.awesome.web.domain.common.datatable.DataTableSearch;
import com.awesome.web.domain.system.SysRole;
import com.awesome.web.domain.system.SysRoleResource;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
     * 根据搜索条件，查询所有相关信息
     * @param search
     * @return
     */
    List<SysRole> listBySearch(@Param("search") DataTableSearch search);

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

    /**
     * 查询所有的角色，并关联用户
     * @param userId
     * @return
     */
    List<SysRole> authorizationByUserId(@Param(value="userId") Long userId);

    /**
     * 获取关联用户数量
     * @param id
     * @return
     */
    @Select( " select count(*) from sys_user_role where role_id =#{id} " )
    int userCount(Long id);


    /**
     * 根据角色id，删除角色和用户关系
     * @param id
     * @return
     */
    @Delete( " delete from sys_user_role where role_id =#{id} " )
    int deleteUserRoleById(Long id);

    /**
     * 根据id删除角色
     * @param id
     * @return
     */
    @Delete( " delete from sys_role where id = #{id} " )
    int deleteById(Long id);
}
