package com.awesome.web.mapper.system;

import com.awesome.web.domain.common.datatable.DataTableSearch;
import com.awesome.web.domain.system.SysUser;
import com.awesome.web.domain.system.SysUserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
     * @param search
     * @return
     */
    List<SysUser> listByUser( @Param("user") SysUser user, @Param("search") DataTableSearch search);

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

    /**
     * 根据用户id获取所有模块id
     * @param id
     * @return
     */
    @Select(" select model_id from sys_user_model where user_id = #{id} ")
    List<Long> findModelsByUserId(Long id);

    /**
     * 根据用户id 删除用户模块关系
     * @param userId
     * @return
     */
    @Delete(" delete from sys_user_model where user_id = #{userId} ")
    int deleteUserModelById(Long userId);

    /**
     * 插入用户 模块关系
     * @param models
     * @param userId
     * @return
     */
    int insertUserModel(@Param("models") Long[] models, @Param("userId") Long userId);

    /**
     * 根据用户名获取用户总数
     * @param username
     * @return
     */
    @Select(" select count(*) from sys_user where username = #{username} ")
    int findCountByUsername(String username);
}
