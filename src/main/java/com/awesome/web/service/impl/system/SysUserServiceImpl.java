package com.awesome.web.service.impl.system;

import com.awesome.util.Md5SaltUtil;
import com.awesome.web.domain.common.ResultMessage;
import com.awesome.web.domain.system.SysUser;
import com.awesome.web.domain.system.SysUserRole;
import com.awesome.web.mapper.system.SysUserMapper;
import com.awesome.web.service.system.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

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

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    @Override
    public SysUser findById(Long id) {
        return sysUserMapper.findById(id);
    }

    /**
     * 保存或更新用户
     *
     * @param user
     * @return
     */
    @Override
    public int saveOrUpdate(SysUser user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        user.setPassword( Md5SaltUtil.getEncryptedPwd(user.getPassword()) );
        if(user.getId()==null){
            return sysUserMapper.insert(user);
        }else{
            return sysUserMapper.update(user);
        }
    }

    /**
     * 用户-角色关系保存
     *
     * @param roles
     * @param userId
     * @return
     */
    @Override
    public int roleSave(Long[] roles, Long userId) {
        sysUserMapper.deleteUserRoleById(userId);
        Set<Long> roleSet = new HashSet<>(Arrays.asList(roles));
        List<SysUserRole> list = new ArrayList<>();
        roleSet.forEach(x-> {
            list.add(new SysUserRole(userId, x));
        });

        if(!list.isEmpty()){
            return sysUserMapper.insertUserRole(list);
        }
        return 0;
    }
}
