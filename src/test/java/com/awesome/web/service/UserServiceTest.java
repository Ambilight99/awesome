package com.awesome.web.service;

import com.awesome.MybatisDemoApplicationTests;
import com.awesome.web.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author adam
 * @ClassName UserServiceTest
 * @Description
 * @create 2017/6/2 14:20
 */
@Transactional
public class UserServiceTest extends MybatisDemoApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    public void queryByUidTest(){
        User u = userService.queryByUid(1);
        userService.updateByUid(1);
        User u2 = userService.queryByUid(1);
    }

}
