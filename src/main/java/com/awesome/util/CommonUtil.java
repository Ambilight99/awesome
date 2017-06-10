package com.awesome.util;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author adam
 * @ClassName CommonUtil
 * @Description 公用的工具
 * @create 2017/6/8 19:18
 */
public class CommonUtil {

    /**
     *
     * @Title: tree
     * @Description: 返回树结构
     * @param tList
     * @param t
     * @param methodName t对象中的方法
     * @param parentMehtodName tList中对象的方法
     * @return
     * @return Map
     * @author adam
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @date 2016年12月31日 下午9:40:44
     */
    @SuppressWarnings({ "unchecked", "rawtypes"})
    public static <T> Map tree(List<T> tList, T t, String methodName, String parentMehtodName) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List result = new ArrayList();
        Map map = new LinkedHashMap();
        //获取父节点元素
        Class<?> tClass =t.getClass();
        Object tCode=  tClass.getMethod(methodName).invoke(t);

        Iterator<T> iterator = tList.iterator();
        while(iterator.hasNext()){
            //获取子节点元素
            T x = iterator.next();
            Class<?> xClass = x.getClass();
            Object xPCode =  xClass.getMethod(parentMehtodName).invoke(x);

            if(tCode.equals(xPCode)){
                Map m =tree(tList,x,methodName,parentMehtodName);
                result.add(m);
            }
        }

        map.put("node", t);
        if(!result.isEmpty()){
            map.put("children", result);
        }
        return map;
    }
}
