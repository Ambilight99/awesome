package com.awesome.web.mapper.system;

import com.awesome.web.domain.common.datatable.DataTableSearch;
import com.awesome.web.domain.system.SysResource;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author adam
 * @ClassName SysResourceMapper
 * @Description 系统资源Mapper
 * @create 2017/6/4 14:23
 */
@Repository
@Mapper
public interface SysResourceMapper {

    /**
     * 根据角色名查找所有的资源
     * @param roleName
     * @return
     */
    @Select( " select * from sys_resource re , sys_role_resource rr ,sys_role ro " +
            "    WHERE  rr.resource_id=re.id and ro.id=rr.role_id AND ro.name=#{roleName} ")
    List<SysResource> listByRoleName(@Param("roleName") String roleName);

    /**
     * 查询所有的资源
     * @param resource
     * @return
     */
    List<SysResource> list(SysResource resource);

    /**
     * 根据条件列出所有资源信息
     * @param resource
     * @param search
     * @param models
     * @return
     */
    List<SysResource> listBySearch(@Param("resource") SysResource resource, @Param("search") DataTableSearch search,  @Param("models") List<Long> models);


    /**
     * 根据角色id查询所有的资源
     * @param role
     * @return
     */
    List<SysResource> listByRole(@Param("role") Long role);

    /**
     * 根据资源条件  查询对应资源
     * @param resource
     * @return
     */
    SysResource findByResource(SysResource resource);

    /**
     * 根据资源id 查询对应资源
     * @param id
     * @return
     */
    SysResource findById(Long id);

    /**
     * 插入一条资源
     * @param resource
     * @return
     */
    int insert(SysResource resource);

    /**
     * 更新一条资源
     * @param resource
     * @return
     */
    int update(SysResource resource);

    /**
     * 根据资源id,删除角色-资源关系
     * @param id
     * @return
     */
    @Delete(" delete from sys_role_resource where resource_id = #{id} ")
    int deleteRoleResourceById(Long id);

    /**
     * 根据资源id 删除资源
     * @param id
     * @return
     */
    @Delete(" delete from sys_resource where id = #{id} ")
    int deleteById(Long id);

    /**
     * 修改资源所属模块
     * @param ids
     * @param model
     * @return
     */
    int move(@Param("ids") Long[] ids, @Param("model") Long model);

    @Select("select count(*) from sys_resource where name = #{name} ")
    int findCountByName(String name);
}
