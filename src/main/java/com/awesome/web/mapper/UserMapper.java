package com.awesome.web.mapper;

import com.awesome.web.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author adam
 * @ClassName UserDao
 * @Description
 * @create 2017/5/31 17:56
 */
@Repository
@Mapper
public interface UserMapper {

    @Select(" select * from user ")
    List<User> listUsers();

    @Select(" select * from user ")
    List<Map> listUsersMap();

    @Select(" select * from user where uid= #{uid}")
    User queryByUid(@Param("uid") int uid);

    User queryByUidXML(@Param("uid") int uid);

    @Insert(" insert into user(username,password,name) values(#{user.username},#{user.password},#{user.name} )")
    @Options(useGeneratedKeys = true,keyProperty ="user.uid" )
    int insertOne( @Param("user") User user);

    @Update(" update user set name='newname' where uid = #{uid} ")
    int updateNameByUid(@Param("uid") int uid);

    User queryByUsername(String username);

    User queryFetchByUsername(String username);
}
