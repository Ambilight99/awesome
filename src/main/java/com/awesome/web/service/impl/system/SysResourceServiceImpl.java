package com.awesome.web.service.impl.system;

import com.awesome.web.domain.system.SysResource;
import com.awesome.web.mapper.system.SysResourceMapper;
import com.awesome.web.service.system.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 根据资源条件 查询
     *
     * @param resource
     * @return
     */
    @Override
    public List<SysResource> list(SysResource resource) {
        return sysResourceMapper.list(resource);
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
}
