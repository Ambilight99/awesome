package com.awesome.util;

import com.alibaba.druid.util.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author adam
 * @ClassName TreeUtil 树工具
 * @Description
 * @create 2017/8/23/023 16:50
 */
public class TreeUtil {

    /**
     *
     * @param list 需要进行树结构 的集合
     * @param <T>
     * @return
     */
    public static <T> List<T> tree(List<T> list){
        return TreeUtil.tree(list, null );
    }

    /**
     *
     * @param list 需要进行树结构 的集合
     * @param parentId 树机构父节点
     * @param <T>
     * @return
     */
    public static <T> List<T> tree(List<T> list , String parentId){
        return TreeUtil.tree(list, parentId , "id", "parentId", "children");
    }

    /**
     * 高效版树结构生成
     * @param list  需要进行树结构 的集合
     * @param parentId  树机构父节点
     * @param idField   标识id的字段
     * @param parentIdField 标识父id的字段
     * @param childrenField 标识子集合的字段
     * @param <T>
     * @return
     */
    public static <T> List<T> tree(List<T> list , String parentId, String idField, String parentIdField, String childrenField){
        Map<String,BeanWrapper> idMap = new HashMap<>();  // 所有节点id 和 节点的map
        Map<String,List> parentIdMap = new HashMap<>();  // 所有节点parentId 和 节点的map
        list.forEach( t->{
            BeanWrapper w = new BeanWrapperImpl(t);
            idMap.put(w.getPropertyValue(idField).toString(), w);

            String pId = w.getPropertyValue(parentIdField).toString();
            if(parentIdMap.containsKey(pId)){
                parentIdMap.get(pId).add(t);
            }else{
                parentIdMap.put(pId,new ArrayList(){{this.add(t);}});
            }

        });

        List rootList = new ArrayList(); //存放完整树结构上的所有根节点
        //循环一次
        for (T t : list) {
            BeanWrapper beanWrapper = new BeanWrapperImpl(t);
            String beanParentId = beanWrapper.getPropertyValue(parentIdField).toString();
            //获取父节点的子集合
            BeanWrapper parentWapper =  idMap.get(beanParentId);
            if(parentWapper == null){
                rootList.add(t);
                continue;
            }
            List children = (List) parentWapper.getPropertyValue(childrenField);
            if (children==null){
                children = new ArrayList();
            }
            //将当期子节点加入到父节点的子集合中。
            children.add(t);
            parentWapper.setPropertyValue(childrenField,children);
        }

        //如果没传进来父节点id,则取所有根节点
        if(StringUtils.isEmpty(parentId)){
            return rootList;
        }else{
            return parentIdMap.get(parentId);
        }
    }


    /**
     * 低效版树结构生成
     * @param list  需要进行树结构 的集合
     * @param parentId  树机构父节点
     * @param idField   标识id的字段
     * @param parentIdField 标识父id的字段
     * @param childrenField 标识子集合的字段
     * @param <T>
     * @return
     */
    @Deprecated
    public static <T> List<T> inefficientTree(List<T> list , String parentId, String idField, String parentIdField, String childrenField){
        List children  = new ArrayList();
        //如果未设置parentId，获取所有最高等级父节点
        if( StringUtils.isEmpty(parentId) ) {
            List<String> ids = new ArrayList<>(); //所有节点id
            if (StringUtils.isEmpty(parentId)) {
                ids = list.stream().map(x -> new BeanWrapperImpl(x).getPropertyValue(idField).toString()).collect(Collectors.toList());
            }
            for (T t : list) {
                BeanWrapper beanWrapper = new BeanWrapperImpl(t);
                String beanId = beanWrapper.getPropertyValue(idField).toString();
                String beanParentId = beanWrapper.getPropertyValue(parentIdField).toString();
                if ( !ids.contains(beanParentId) ) {  //如果该树对象的父节点不存在，那么它本身就是父节点
                    beanWrapper.setPropertyValue(childrenField, inefficientTree(list, beanId,idField,parentIdField,childrenField));
                    children.add(t);
                }
            }
        }else {
            for(T t : list){
                BeanWrapper beanWrapper = new BeanWrapperImpl(t);
                String beanId = beanWrapper.getPropertyValue(idField).toString();
                String beanParentId = beanWrapper.getPropertyValue(parentIdField).toString();
                if( Objects.equals(beanParentId, parentId) ){
                    beanWrapper.setPropertyValue(childrenField,inefficientTree(list,beanId,idField,parentIdField,childrenField));
                    children.add(t);
                }
            }
        }
        return  children.isEmpty()?null:children;
    }
}
