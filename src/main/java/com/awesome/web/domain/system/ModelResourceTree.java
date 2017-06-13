package com.awesome.web.domain.system;

import java.util.ArrayList;

/**
 * @author adam
 * @ClassName ModelResourceTree
 * @Description 模型和资源的树结构bean
 * @create 2017/6/10 13:59
 */
public class ModelResourceTree extends BaseZTree {
    private static final String MODEL ="model";
    private static final String RESOURCE ="resource";

    /**
     * 将模块或资源bean 转换成树结构bean
     * @param obj
     * @return
     */
    public static ModelResourceTree convert(Object obj){
        ModelResourceTree treeObj = new ModelResourceTree();
        if(obj instanceof SysModel){
            SysModel model = (SysModel) obj;
            treeObj.setId( model.getId().toString() );
            treeObj.setName( model.getName() );
            treeObj.setOpen( model.getOpen() );
            treeObj.setpId( model.getParent().toString());
            treeObj.setParent( model.getParent() );
            treeObj.setChildren(new ArrayList<>());
            treeObj.setType(ModelResourceTree.MODEL);
            treeObj.setIconSkin("model_icon");
            treeObj.setOrder( model.getOrder() );
        }else if(obj instanceof SysResource){
            SysResource resource = (SysResource) obj;
            treeObj.setId( resource.getId().toString() );
            treeObj.setName( resource.getName() );
            treeObj.setOpen( false );
            treeObj.setpId(resource.getModel().toString());
            treeObj.setParent( resource.getModel() );
            treeObj.setType(ModelResourceTree.RESOURCE);
            treeObj.setIconSkin("url_icon");
        }
        return treeObj;
    }


    public static ModelResourceTree convert(Object obj,boolean checked){
        ModelResourceTree tree = ModelResourceTree.convert(obj);
        tree.setChecked(checked);
        return tree;
    }

}
