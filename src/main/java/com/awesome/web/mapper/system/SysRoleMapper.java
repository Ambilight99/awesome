package com.awesome.web.mapper.system;

import com.awesome.web.domain.system.SysRole;
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
    public List<SysRole> listAll();
}
