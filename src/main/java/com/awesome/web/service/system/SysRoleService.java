package com.awesome.web.service.system;

import com.awesome.web.domain.system.SysRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author adam
 * @ClassName SysRoleServiceImpl
 * @Description 系统角色Service
 * @create 2017/6/10 11:50
 */
@Transactional
public interface SysRoleService {

    /**
     * 根据角色里面的搜索条件，查询所有相关信息
     * @param role
     * @return
     */
    List<SysRole> list(SysRole role);

    /**
     * 根据id查询角色
     * @param id
     * @return
     */
    SysRole findById(Long id);

    /**
     * 保存或者更新
     * @param role
     * @return
     */
    int saveOrUpdate(SysRole role);

    /**
     * 保存角色、资源关系
     * @param roleId
     * @param resourceIds
     * @return
     */
    int resourceSave(Long roleId, Long[] resourceIds);

    /**
     *  查询所有的角色，并关联用户
     * @param userId
     * @return
     */
    List<SysRole> authorizationByUserId(Long userId);
}
