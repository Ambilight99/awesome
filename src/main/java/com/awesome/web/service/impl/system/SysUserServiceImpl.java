package com.awesome.web.service.impl.system;

import com.awesome.web.domain.system.SysUser;
import com.awesome.web.mapper.system.SysUserMapper;
import com.awesome.web.service.system.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author adam
 * @ClassName UserServiceImpl
 * @Description
 * @create 2017/6/2 11:54
 */
@Service
@CacheConfig(cacheNames = "DEFAULT_CACHE")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;


    /**
     * 列出所有用户信息
     *
     * @return
     */
    @Override
    public List<SysUser> list(SysUser user) {
        return sysUserMapper.listByUser(user);
    }
}
