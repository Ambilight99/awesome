package com.awesome.util;

import com.alibaba.druid.util.StringUtils;
import com.awesome.web.domain.system.SysDepartment;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author adam
 * @ClassName TreeUtil
 * @Description 树工具接口
 * @create 2017/6/15 18:14
 */
public class TreeUtil{

//    /**
//     * 递归获取所有的子节点id
//     * @param id
//     * @param departments
//     * @return
//     */
//    public static List<Long> getAllChildren(Long id, List<?> departments){
//        List<Long> ids = new ArrayList<>();
//        ids.add(id);
//        return TreeUtil.getAllChildren(ids,departments);
//    }
//
//    /**
//     * 递归获取所有的子节点
//     * @param ids
//     * @param departments
//     * @return
//     */
//    public static List<Long> getAllChildren(List<Long> ids,List<?> list ,String methodName, String parentMehtodName){
//        List<Long> childrenIds = new ArrayList<>();
//        //获取父节点元素
//        Class<?> tClass =t.getClass();
//        Object tCode=  tClass.getMethod(methodName).invoke(t);
//
//        if(ids.isEmpty()){
//            return childrenIds;
//        }
//        for( Long id : ids){
//            for( SysDepartment department : departments ){
//                if( Objects.equals(id, department.getParent()) ){
//                    childrenIds.add(department.getId());
//                }
//            }
//        }
//        ids.addAll( getAllChildren(childrenIds,departments) );
//        return ids;
//    }


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
     *
     * @param list  需要进行树结构 的集合
     * @param parentId  树机构父节点
     * @param idField   标识id的字段
     * @param parentIdField 标识父id的字段
     * @param childrenField 标识子集合的字段
     * @param <T>
     * @return
     */
    public static <T> List<T> tree(List<T> list , String parentId, String idField, String parentIdField, String childrenField){
        List children  = new ArrayList();
        //parentId，获取所有最高等级父节点
        if( StringUtils.isEmpty(parentId) ){
            Set<String> ids = list.stream().map(x-> {
                BeanWrapper beanWrapper = new BeanWrapperImpl(x);
                return beanWrapper.getPropertyValue(idField).toString();
            }).collect(Collectors.toSet());
            System.out.println(ids);
            for(T obj : list){
                BeanWrapper beanWrapper = new BeanWrapperImpl(obj);
                String beanId = beanWrapper.getPropertyValue(idField).toString();
                String beanParentId = beanWrapper.getPropertyValue(parentIdField).toString();
                if( ! ids.contains( beanParentId )){
                    beanWrapper.setPropertyValue(childrenField,tree( list, beanId) );
                    children.add(obj);
                }
            }
        }else{
            for(T obj : list){
                BeanWrapper beanWrapper = new BeanWrapperImpl(obj);
                String beanId = beanWrapper.getPropertyValue(idField).toString();
                String beanParentId = beanWrapper.getPropertyValue(parentIdField).toString();
                List beanChildren = (List)beanWrapper.getPropertyValue(childrenField);
                if(Objects.equals(beanParentId, parentId )){
                    children.add(obj);
                    if( beanChildren ==null ){
                        beanWrapper.setPropertyValue(childrenField,tree(list,beanId));
                    }else{
                        List newObjChildren = tree(list,beanId );
                        newObjChildren.addAll(beanChildren );
                        beanWrapper.setPropertyValue(childrenField,newObjChildren);
                    }
                }
            }
        }
        return  children;
    }


}
