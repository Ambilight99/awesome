package com.awesome.web.dao;

import com.alibaba.fastjson.JSON;
import com.awesome.MybatisDemoApplicationTests;
import com.awesome.web.domain.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author adam
 * @ClassName UserMapperTest
 * @Description
 * @create 2017/5/31 17:59
 */
@Transactional
public class UserMapperTest extends MybatisDemoApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(UserMapperTest.class);

    @Autowired
    private UserMapper userMapper;

    @Test
    public void listUsersTest(){
        List<User> userList =userMapper.listUsers();
        for (int i = 0; i < userList.size(); i++) {
            User user =  userList.get(i);
            logger.info(user.toString());
        }
    }

    @Test
    public void listUsersMapTest(){
        List<Map> userList = userMapper.listUsersMap();
        for (int i = 0; i < userList.size(); i++) {
            Map map =  userList.get(i);
            logger.info(" {} - {} ",map.get("uid"),map.get("username"));
        }
    }

    @Test
    public void insertOneTest(){
        User user = new User();
        user.setUsername("adam");
        user.setPassword("123456");
        user.setName("亚当");
        userMapper.insertOne(user);
        User userDb = userMapper.queryByUid(user.getUid());
        logger.info("用户的名称为{}",userDb.getName());
        Assert.assertEquals(user.getName(),userDb.getName());
    }

    @Test
    public void listUsersByPage(){
        PageHelper.startPage(0,2);
        List<User> userList = userMapper.listUsers();
        logger.info(JSON.toJSONString(new PageInfo(userList)));
        Assert.assertEquals(userList.size(),2);

    }


}
