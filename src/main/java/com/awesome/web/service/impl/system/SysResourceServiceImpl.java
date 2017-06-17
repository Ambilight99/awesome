package com.awesome.web.service.impl.system;

import com.awesome.web.domain.common.datatable.DataTableSearch;
import com.awesome.web.domain.system.BaseZTree;
import com.awesome.web.domain.system.SysDepartment;
import com.awesome.web.domain.system.SysModel;
import com.awesome.web.domain.system.SysResource;
import com.awesome.web.mapper.system.SysModelMapper;
import com.awesome.web.mapper.system.SysResourceMapper;
import com.awesome.web.service.system.SysResourceService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author adam
 * @ClassName SysResourceServiceImpl
 * @Description 系统资源 ServiceImpl
 * @create 2017/6/8 18:19
 */
@Service
public class SysResourceServiceImpl implements SysResourceService {

    @Autowired
    private SysResourceMapper sysResourceMapper;
    @Autowired
    private SysModelMapper sysModelMapper;

    /**
     * 根据资源条件 查询
     *
     * @param resource
     * @return
     */
    @Override
    public List<SysResource> list(SysResource resource){
        return sysResourceMapper.list(resource);
    }

    /**
     * 根据资源条件 查询
     *
     * @param resource
     * @param search
     * @return
     */
    @Override
    public List<SysResource> list(SysResource resource,DataTableSearch search) {
        PageHelper.offsetPage(search.getStart(),search.getLength());
        return sysResourceMapper.listBySearch(resource,search,null);
    }

    /**
     * 根据条件 查询
     *
     * @param resource
     * @param search
     * @param subdivision
     * @return
     */
    @Override
    public List<SysResource> list(SysResource resource, DataTableSearch search, boolean subdivision) {
        List<Long> models = new ArrayList<>();
        if(subdivision){
            models = this.getAllChildren(resource.getModel(), sysModelMapper.list());
        }else{
            models.add( resource.getModel() );
        }
        PageHelper.offsetPage(search.getStart(),search.getLength());
        return sysResourceMapper.listBySearch(resource,search,models);
    }

    /**
     * 根据id查找资源
     *
     * @param id
     * @return
     */
    @Override
    public SysResource findById(Long id) {
        return sysResourceMapper.findById(id);
    }

    /**
     * 保存或者更新资源
     *
     * @param resource
     * @return
     */
    @Override
    public int saveOrUpdate(SysResource resource) {
        if(resource.getId()==null){
            return sysResourceMapper.insert(resource);
        }else{
            return  sysResourceMapper.update(resource);
        }
    }

    /**
     * 根据id删除一条资源，以及资源与角色的关联
     *
     * @param id
     * @return
     */
    @Override
    public int deleteById(Long id) {
        sysResourceMapper.deleteRoleResourceById(id);
        return sysResourceMapper.deleteById(id);
    }

    /**
     * 将资源移动到模块
     * @param ids
     * @param model
     * @return
     */
    @Override
    public int move(Long[] ids, Long model) {
        return  sysResourceMapper.move(ids, model);
    }


    /**
     * 递归获取所有的子节点
     * @param id
     * @param models
     * @return
     */
    private List<Long> getAllChildren(Long id,List<SysModel> models){
        List<Long> ids = new ArrayList<>();
        ids.add(id);
        return this.getAllChildren(ids,models);
    }

    /**
     * 递归获取所有的子节点
     * @param ids
     * @param models
     * @return
     */
    private List<Long> getAllChildren(List<Long> ids,List<SysModel> models){
        List<Long> childrenIds = new ArrayList<>();
        if(ids.isEmpty()){
            return childrenIds;
        }
        for( Long id : ids){
            for( SysModel model : models ){
                if( Objects.equals(id, model.getParent()) ){
                    childrenIds.add(model.getId());
                }
            }
        }
        ids.addAll( getAllChildren(childrenIds,models) );
        return ids;
    }
}
