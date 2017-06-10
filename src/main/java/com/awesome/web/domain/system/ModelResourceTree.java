package com.awesome.web.domain.system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @author adam
 * @ClassName ModelResourceTree
 * @Description 模型和资源的树结构bean
 * @create 2017/6/10 13:59
 */
public class ModelResourceTree implements Serializable {
    private static final String MODEL ="model";
    private static final String RESOURCE ="resource";

    private Long id;
    private String name;
    /** 是否展开   */
    private boolean open;
    /** 父节点 */
    private Long parent;
    /** 子节点 */
    private List<ModelResourceTree> children;
    /** 类型 model 或者resource  **/
    private String type;
    private Integer order;
//    private String icon;
//    private String iconClose;
//    private String iconOpen;
    /** 树图标 */
    private String iconSkin;
    /** 是否默认选中 */
    private Boolean checked;

    /**
     * 将模块或资源bean 转换成树结构bean
     * @param obj
     * @return
     */
    public static ModelResourceTree convert(Object obj){
        ModelResourceTree treeObj = new ModelResourceTree();
        if(obj instanceof SysModel){
            SysModel model = (SysModel) obj;
            treeObj.setId( model.getId() );
            treeObj.setName( model.getName() );
            treeObj.setOpen( model.isOpen() );
            treeObj.setParent( model.getParent() );
            treeObj.setChildren(new ArrayList<>());
            treeObj.setType(ModelResourceTree.MODEL);
            treeObj.setIconSkin("model_icon");
            treeObj.setOrder( model.getOrder() );
        }else if(obj instanceof SysResource){
            SysResource resource = (SysResource) obj;
            treeObj.setId( resource.getId() );
            treeObj.setName( resource.getName() );
            treeObj.setOpen( false );
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




    /**
     * 树形结构
     * @param list  树集合
     * @param rootId    根节点id
     * @param level
     * @return
     */

    public static List<ModelResourceTree> treeModel(List<ModelResourceTree> list, Long rootId ,int level){
        level++;
        Iterator<ModelResourceTree> iterator = list.iterator();
        List<ModelResourceTree> children = new ArrayList<>();
        while(iterator.hasNext()){
            //获取子节点元素
            ModelResourceTree treeObj = iterator.next();
            if(Objects.equals( rootId ,treeObj.getParent() )){
                if(level <= 2 ){
                    treeObj.setOpen(true);
                }
                children.add( treeObj );

                if(treeObj.getChildren()==null){
                    treeObj.setChildren(treeModel(list,treeObj.getId(), level ));
                }else{
                    List<ModelResourceTree> newObjChildren = treeModel(list,treeObj.getId(), level );
                    newObjChildren.addAll(treeObj.getChildren() );      //让模块靠前，资源靠后
                    treeObj.setChildren(newObjChildren);
                }
            }
        }
        return children;
    }


    /************************************setter/getter********************************/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public List<ModelResourceTree> getChildren() {
        return children;
    }

    public void setChildren(List<ModelResourceTree> children) {
        this.children = children;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getIconSkin() {
        return iconSkin;
    }

    public void setIconSkin(String iconSkin) {
        this.iconSkin = iconSkin;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
