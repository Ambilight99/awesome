package com.awesome.web.mapper.system;

import com.awesome.web.domain.system.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    List<SysUser> listByUser(@Param("user") SysUser user);

}
