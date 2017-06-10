package com.awesome.web.mapper;

import com.awesome.MybatisDemoApplicationTests;
import com.awesome.web.domain.system.SysUser;
import com.awesome.web.mapper.system.SysUserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author adam
 * @ClassName SqlSessionTest
 * @Description
 * @create 2017/6/2 10:32
 */
public class SqlSessionTest extends MybatisDemoApplicationTests{
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    /**
     * 测试一级缓存
     */
    @Test
    public void testCache1(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);

 //       SysUser user1 = userMapper.queryByUid(1);

 //       sqlSession.clearCache();
//        userMapper.updateNameByUid(1);
//        sqlSession.commit();
//        SysUser user2 = userMapper.queryByUid(1);
        sqlSession.close();

    }

}
