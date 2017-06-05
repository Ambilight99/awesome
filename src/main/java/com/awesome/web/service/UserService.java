package com.awesome.web.service;

import com.awesome.web.domain.User;

/**
 * @author adam
 * @ClassName UserService
 * @Description 用户操作Service
 * @create 2017/6/2 11:53
 */
public interface UserService {

    /**
     * 根据uid查询用户
     * @param uid
     * @return
     */
    User queryByUid(int uid);

    /**
     * 根据uid更新用户
     * @param uid
     * @return
     */
    int updateByUid(int uid);
}
