package com.awesome.web.controller;

import com.awesome.web.domain.User;
import com.awesome.web.mapper.UserMapper;
import com.awesome.web.service.UserService;
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
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/username/{username}")
    public User queryByUsername(@PathVariable("username") String username){
        return userMapper.queryByUsername("fxw");
    }



    /**
     * 延迟加载 角色信息
     * @param username
     * @return
     */
    @RequestMapping("/username/fetch/{username}")
    public User queryFetchByUsername(@PathVariable("username") String username){
        User user = userMapper.queryFetchByUsername("fxw");
        User u = new User();
        u.setName(user.getName());
        u.setRoles(user.getRoles()); //注释掉 此行，则不会执行userMapper 中的queryRolesByUid查询语句
        return u;
    }

    @RequestMapping("/uid/{uid}")
    public User queryByUid(@PathVariable("uid") int uid){
    //    User u =userMapper.queryByUidXML(uid);
    //    userMapper.updateNameByUid(uid);
    //    User u2 = userMapper.queryByUidXML(uid);
        User u = userService.queryByUid(uid);
        User u2 = userService.queryByUid(uid);

        return u2;
    }




}
