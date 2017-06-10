package com.awesome.web.service.impl.system;

import com.awesome.web.domain.system.SysRole;
import com.awesome.web.domain.system.SysRoleResource;
import com.awesome.web.mapper.system.SysRoleMapper;
import com.awesome.web.service.system.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author adam
 * @ClassName SysRoleServiceImpl
 * @Description 系统角色ServiceImpl
 * @create 2017/6/10 11:50
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 根据角色里面的搜索条件，查询所有相关信息
     *
     * @param role
     * @return
     */
    @Override
    public List<SysRole> list(SysRole role) {
        if(role == null){
            return new ArrayList<>();
        }
        return sysRoleMapper.listByRole(role);
    }

    /**
     * 根据id查询角色
     *
     * @param id
     * @return
     */
    @Override
    public SysRole findById(Long id) {
        return sysRoleMapper.findById(id);
    }

    /**
     * 保存或者更新
     *
     * @param role
     * @return
     */
    @Override
    public int saveOrUpdate(SysRole role) {
        if(role.getId()==null){
            return sysRoleMapper.insert(role);
        }else{
            return  sysRoleMapper.update(role);
        }
    }

    /**
     * 保存角色、资源关系
     *
     * @param roleId
     * @param resourceIds
     * @return
     */
    @Override
    public int resourceSave(Long roleId, Long[] resourceIds) {
        sysRoleMapper.deleteRoleResourceById(roleId);
        List<SysRoleResource> roleResources = new ArrayList<>();
        for(Long resourceId : resourceIds){
            SysRoleResource roleResource = new SysRoleResource();
            roleResource.setRoleId(roleId);
            roleResource.setResourceId(resourceId);
            roleResources.add(roleResource);
        }
        if(roleResources.isEmpty()){
            return 0;
        }else{
            return sysRoleMapper.insertRoleResourceBatch(roleResources);
        }
    }
}
