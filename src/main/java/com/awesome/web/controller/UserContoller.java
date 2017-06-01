package com.awesome.web.controller;

import com.awesome.web.domain.User;
import com.awesome.web.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author adam
 * @ClassName UserContollre
 * @Description
 * @create 2017/6/1 14:13
 */
@RestController
public class UserContoller {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/username/{username}")
    public User queryByUsername(@PathVariable("username") String username){
        return userMapper.queryByUsername("fxw");
    }
}
