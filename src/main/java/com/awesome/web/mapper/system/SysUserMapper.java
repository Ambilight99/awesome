package com.awesome.web.mapper.system;

import com.awesome.web.domain.system.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author adam
 * @ClassName SysUserMapper
 * @Description 系统用户Mapper
 * @create 2017/6/3 14:12
 */
@Repository
@Mapper
public interface SysUserMapper {

    SysUser findByUsername(String username);

}
