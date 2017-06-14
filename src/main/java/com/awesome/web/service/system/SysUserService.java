package com.awesome.web.service.system;

import com.awesome.web.domain.common.ResultMessage;
import com.awesome.web.domain.system.SysUser;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author adam
 * @ClassName SysUserService
 * @Description 系统用户操作Service
 * @create 2017/6/2 11:53
 */
@Transactional
public interface SysUserService {

    /**
     * 列出所有用户信息
     * @return
     */
    List<SysUser> list(SysUser user);

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    SysUser findById(Long id);

    /**
     * 保存或更新用户
     * @param user
     * @return
     */
    int saveOrUpdate(SysUser user) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    /**
     *
     * 用户-角色关系保存
     * @param roles
     * @param userId
     * @return
     */
    int roleSave(Long[] roles, Long userId);
}
