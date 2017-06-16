package com.awesome.web.service.system;

import com.awesome.web.domain.common.datatable.DataTableSearch;
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
    List<SysUser> list(SysUser user,DataTableSearch search);

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

    /**
     * 删除用户
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 根据条件列出所有用户信息
     * @param user
     * @param search
     * @param subdivision
     * @return
             */
    List<SysUser> list(SysUser user, DataTableSearch search, boolean subdivision );
}
