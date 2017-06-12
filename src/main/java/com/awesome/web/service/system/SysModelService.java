package com.awesome.web.service.system;

import com.awesome.web.domain.system.BaseZTree;
import com.awesome.web.domain.system.ModelResourceTree;
import com.awesome.web.domain.system.SysModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author adam
 * @ClassName SysModelService
 * @Description 系统资源模块 Service
 * @create 2017/6/2 11:53
 */
@Transactional
public interface SysModelService {

    /**
     * 列出所有模块
     * @return
     */
    List<SysModel> list();

    /**
     * 模块树结构
     * @return
     */
    List<BaseZTree> treeData();

    /**
     * 模块资源树结构
     * @return
     */
    List<BaseZTree> resourceTreeData(Long roleId);

    /**
     * 保存或者更新
     * @param model
     * @return
     */
    int saveOrUpdate(SysModel model);

    /**
     * 根据id查询模块信息
     * @param id
     * @return
     */
    SysModel findById(Long id);

    /**
     * 根据 简写abbr 查询模块信息
     * @param abbr
     * @return
     */
    SysModel findByAbbr(String abbr);

}
