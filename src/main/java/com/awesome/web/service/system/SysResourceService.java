package com.awesome.web.service.system;

import com.awesome.web.domain.system.SysResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author adam
 * @ClassName SysResourceService
 * @Description 系统资源 Service
 * @create 2017/6/2 11:53
 */
@Transactional
public interface SysResourceService {

    /**
     * 根据资源条件 查询
     * @param resource
     * @return
     */
    List<SysResource> list(SysResource resource);
}
