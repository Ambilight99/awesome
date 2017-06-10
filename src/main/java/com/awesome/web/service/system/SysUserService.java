package com.awesome.web.service.system;

import com.awesome.web.domain.system.SysUser;
import org.springframework.transaction.annotation.Transactional;

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
    List<SysUser> list();
}
