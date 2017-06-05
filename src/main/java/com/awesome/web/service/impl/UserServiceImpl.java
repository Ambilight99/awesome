package com.awesome.web.service.impl;

import com.awesome.web.domain.User;
import com.awesome.web.mapper.UserMapper;
import com.awesome.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author adam
 * @ClassName UserServiceImpl
 * @Description
 * @create 2017/6/2 11:54
 */
@Service
@CacheConfig(cacheNames = "DEFAULT_CACHE")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据uid查询用户
     *
     * @param uid
     * @return
     */
    @Cacheable
    @Override
    public User queryByUid(int uid) {
        return userMapper.queryByUid(uid);
    }

    /**
     * 根据uid更新用户
     *
     * @param uid
     * @return
     */
    @Override
    public int updateByUid(int uid) {
        return userMapper.updateNameByUid(1);
    }
}
