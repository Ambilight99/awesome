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
}
