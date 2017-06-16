package com.awesome.util;

import com.awesome.web.domain.system.SysDepartment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

}
