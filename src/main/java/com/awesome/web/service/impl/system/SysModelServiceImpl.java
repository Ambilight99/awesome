package com.awesome.web.service.impl.system;

import com.alibaba.fastjson.JSON;
import com.awesome.web.domain.system.ModelResourceTree;
import com.awesome.web.domain.system.SysModel;
import com.awesome.web.domain.system.SysResource;
import com.awesome.web.mapper.system.SysModelMapper;
import com.awesome.web.mapper.system.SysResourceMapper;
import com.awesome.web.service.system.SysModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author adam
 * @ClassName SysModelServiceImpl
 * @Description 系统资源模块 ServiceImpl
 * @create 2017/6/8 18:19
 */
@Service
public class SysModelServiceImpl implements SysModelService {

    @Autowired
    private SysModelMapper sysModelMapper;
    @Autowired
    private SysResourceMapper sysResourceMapper;

    /**
     * 列出所有模块
     *
     * @return
     */
    @Override
    public List<SysModel> list() {
        return sysModelMapper.list();
    }

    /**
     * 模块树结构
     *
     * @return
     */
    @Override
    public List<ModelResourceTree> treeData() {
        List<SysModel> models = sysModelMapper.list();
        List<ModelResourceTree> treeObjs = new ArrayList<>();
        models.forEach(x->{
            treeObjs.add( ModelResourceTree.convert(x) );
        });
        return ModelResourceTree.treeModel(treeObjs,0L,0);
    }

    /**
     * 模块树结构
     *
     * @return
     */
    @Override
    public List<ModelResourceTree> resourceTreeData(Long roleId) {
        Set<Long> resourceIdOfRole = new HashSet<>();  //角色下的所有资源id
        if(roleId!=null){       //查询该模块下的所有资源
            List<SysResource> resourcesOfRole =  sysResourceMapper.listByRole(roleId);
            resourcesOfRole.forEach(x->{
                resourceIdOfRole.add(x.getId());
            });
        }

        //查询所有的模块
        List<SysModel> models = sysModelMapper.list();
        //模块bean转换成tree的Bean
        List<ModelResourceTree> treeObjs = new ArrayList<>();
        for(SysModel model : models){
            treeObjs.add( ModelResourceTree.convert(model) );
        }

        //查询所有的资源
        List<SysResource> resources = sysResourceMapper.list(new SysResource());
        for(SysResource resource : resources){
           for(ModelResourceTree tree : treeObjs){
               if( Objects.equals( resource.getModel(), tree.getId()) ){
                   if(resourceIdOfRole.contains( resource.getId() )){   //如果角色包含，就默认选中
                       tree.getChildren().add(ModelResourceTree.convert(resource,true) );
                   }else{
                       tree.getChildren().add(ModelResourceTree.convert(resource,false) );
                   }
               }
           }
        }




        return ModelResourceTree.treeModel(treeObjs,0L,0);
    }

    /**
     * 保存或者更新
     *
     * @param model
     * @return
     */
    @Override
    public int saveOrUpdate(SysModel model) {
        int count;
        if( model.getId() == null ){
            count = sysModelMapper.insert(model);
        }else{
            count = sysModelMapper.update(model);
        }
        return count;
    }

    /**
     * 根据id查询模块信息
     *
     * @param id
     * @return
     */
    @Override
    public SysModel findById(Long id) {
        SysModel model = sysModelMapper.findById(id);
        return model;
    }

    /**
     * 根据 简写abbr 查询模块信息
     *
     * @param abbr
     * @return
     */
    @Override
    public SysModel findByAbbr(String abbr) {
        SysModel model = sysModelMapper.findByAbbr(abbr);
        return model;
    }
}
