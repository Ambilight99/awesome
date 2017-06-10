package com.awesome.web.mapper.system;

import com.awesome.web.domain.system.SysModel;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author adam
 * @ClassName SysModelMapper
 * @Description 系统资源模块Mapper
 * @create 2017/6/8 18:44
 */
@Repository
@Mapper
public interface SysModelMapper {

    /**
     * 列出所有系统模块
     * @return
     */
    @Select(" select * from sys_model order by `order` asc ")
    List<SysModel> list();

    /**
     * 添加一条系统模块
     * @param model
     * @return
     */
    @Insert(" INSERT INTO sys_model( `name`,`abbr`,`description`,`parent`,`order` ) VALUES ( #{name},#{abbr},#{description},#{parent},#{order} )  ")
    @Options(useGeneratedKeys = true ,keyProperty = "id")
    int insert(SysModel model);

    /**
     * 编辑一条系统模块
     * @param model
     * @return
     */
    int update(SysModel model);

    /**
     * 删除一条系统模块
     * @param id
     * @return
     */
    @Delete(" delete sys_model where id = #{id} ")
    int delete(int id);

    /**
     * 根据id查询模块信息
     * @param id
     * @return
     */
    @Select(" select * from sys_model where id =#{id} ")
    SysModel findById(Long id);

    @Select(" select * from sys_model where abbr= #{abbr} ")
    SysModel findByAbbr(String abbr);
}
