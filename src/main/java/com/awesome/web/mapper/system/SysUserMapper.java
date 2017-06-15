package com.awesome.web.mapper.system;

import com.awesome.web.domain.common.datatable.DataTableSearch;
import com.awesome.web.domain.system.SysUser;
import com.awesome.web.domain.system.SysUserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author adam
 * @ClassName SysUserMapper
 * @Description 系统用户Mapper
 * @create 2017/6/3 14:12
 */
@Repository
@Mapper
public interface SysUserMapper {
    /**
     * 根据登录名查找用户信息
     * @param username
     * @return
     */
    SysUser findByUsername(String username);

    /**
     * 根据用户条件进行搜索
     * @param user
     * @return
     */
    List<SysUser> listByUser(SysUser user);

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    SysUser findById(Long id);

    /**
     * 插入用户
     * @param user
     * @return
     */
    int insert(SysUser user);

    /**
     * 更新用户
     * @param user
     * @return
     */
    int update(SysUser user);

    /**
     * 根据用户id删除用户角色关系
     * @param userId
     * @return
     */
    @Delete( " delete from sys_user_role where user_id = #{userId} " )
    int deleteUserRoleById( @Param(value="userId") Long userId);

    /**
     * 插入用户角色关系
     * @param list
     * @return
     */
    int insertUserRole(List<SysUserRole> list);

    /**
     * 根据条件列出所有用户信息
     * @param user
     * @param search
     * @param departments
     * @return
     */
    List<SysUser> listBySearch( @Param("user") SysUser user, @Param("search") DataTableSearch search, @Param("departments") List<Long> departments);
}
