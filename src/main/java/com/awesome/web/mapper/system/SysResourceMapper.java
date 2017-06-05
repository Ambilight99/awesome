package com.awesome.web.mapper.system;

import com.awesome.web.domain.system.SysResource;
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

    @Select( " select * from sys_resource re , sys_role_resource rr ,sys_role ro " +
            "    WHERE  rr.resource_id=re.id and ro.id=rr.role_id AND ro.name=#{roleName} ")
    List<SysResource> listByRoleName(@Param("roleName") String roleName);
}
