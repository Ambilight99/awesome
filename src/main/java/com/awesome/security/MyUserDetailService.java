package com.awesome.security;

import com.awesome.web.domain.system.SysUser;
import com.awesome.web.mapper.system.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author adam
 * @ClassName MyUserDetailService
 * @Description MyUserDetailService类  在这个类中， 你就可以从数据库中读入用户的密码， 角色信息， 是否锁定， 账号是否过期等
 * @create 2017/6/3 14:07
 */
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //从数据库中读入用户的密码， 角色信息， 是否锁定， 账号是否过期等
        SysUser user = sysUserMapper.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("用户名不存在！");
        }
        return user;
    }
}
