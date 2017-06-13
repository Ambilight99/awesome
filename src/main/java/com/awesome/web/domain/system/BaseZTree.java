package com.awesome.web.domain.system;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @author adam
 * @ClassName BaseZTree
 * @Description ztree结构的抽象类
 * @create 2017/6/11 11:27
 */
public class BaseZTree implements Serializable {
    private String id;
    private String name;
    /** 是否展开   */
    private Boolean open;
    /** 父节点 */
    private String pId;
    /** 父节点 */
    private Long parent;
    /** 子节点 */
    private List<BaseZTree> children;
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
    public static BaseZTree convert(Object obj){
        BaseZTree treeObj = new BaseZTree();
        BeanUtils.copyProperties(obj,treeObj);
        return treeObj;
    }


    /**
     * 是否选中树节点
     * @param obj
     * @param checked
     * @return
     */
    public static BaseZTree convert(Object obj,boolean checked){
        BaseZTree tree = BaseZTree.convert(obj);
        tree.setChecked(checked);
        return tree;
    }


    /**
     * 树形结构
     * @param list  树集合
     * @param rootId    根节点id
     * @param curLevel  当前层级
     * @param openLevel 默认展开几层
     * @return
     */

    private static List<BaseZTree> treeModel(List<? extends  BaseZTree> list, String rootId ,int curLevel,int openLevel){
        curLevel++;
        Iterator<? extends BaseZTree> iterator = list.iterator();
        List<BaseZTree> children = new ArrayList<>();
        while(iterator.hasNext()){
            //获取子节点元素
            BaseZTree treeObj = iterator.next();
            if(Objects.equals( rootId ,treeObj.getParent().toString() )){
                if(curLevel <= openLevel ){
                    treeObj.setOpen(true);
                }
                children.add( treeObj );

                if(treeObj.getChildren()==null){
                    treeObj.setChildren(treeModel(list, treeObj.getId(), curLevel ));
                }else{
                    List<BaseZTree> newObjChildren = treeModel(list,treeObj.getId(), curLevel );
                    newObjChildren.addAll(treeObj.getChildren() );      //让模块靠前，资源靠后
                    treeObj.setChildren(newObjChildren);
                }
            }
        }
        return children;
    }

    /**
     * 树形结构
     * @param list  树集合
     * @param rootId    根节点id
     * @param openLevel 默认展开几层
     * @return
     */
    public static List<BaseZTree> treeModel(List<? extends  BaseZTree> list, String rootId,int openLevel){
        return treeModel(list, rootId ,0,openLevel);
    }

    /**
     * 树形结构(默认根id为0)
     * @param list  树集合
     * @param openLevel 默认展开几层
     * @return
     */
    public static List<BaseZTree> treeModel(List<? extends  BaseZTree> list,int openLevel){
        return treeModel(list, "0" ,0,openLevel);
    }

    public String getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id.toString();
    }

    /************************************setter/getter********************************/


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.pId = parent.toString();
        this.parent = parent;
    }

    public List<BaseZTree> getChildren() {
        return children;
    }

    public void setChildren(List<BaseZTree> children) {
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

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }
}
