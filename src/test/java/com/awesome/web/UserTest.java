package com.awesome.web;

import com.awesome.MybatisDemoApplicationTests;
import com.awesome.web.domain.common.datatable.DataTableSearch;
import com.awesome.web.domain.system.SysDepartment;
import com.awesome.web.domain.system.SysUser;
import com.awesome.web.mapper.system.SysUserMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @author adam
 * @ClassName UserTest
 * @Description User测试
 * @create 2017/6/15 8:27
 */
public class UserTest extends MybatisDemoApplicationTests {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     *
     */
    @Test
    public void testUserListBySearch(){
        SysUser user = new SysUser();
        DataTableSearch dataTableSearch = new DataTableSearch();
        Map<DataTableSearch.Search,String> search = new HashMap<>();
        search.put(DataTableSearch.Search.value,"111");
        search.put(DataTableSearch.Search.regex,"2222");
        dataTableSearch.setSearch(search);

        List<Long> departments = Arrays.asList(new Long[]{1L,2L,9L,7L});

        List<SysUser> users = sysUserMapper.listBySearch(user,dataTableSearch,departments);
        for(SysUser u : users){
            System.out.println(u.getId() + "_" + u.getName()+"_"+u.getDepartmentName());
        }


    }
}
