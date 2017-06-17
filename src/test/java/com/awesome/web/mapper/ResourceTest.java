package com.awesome.web.mapper;

import com.awesome.AwesomeApplicationTests;
import com.awesome.web.domain.common.datatable.DataTableSearch;
import com.awesome.web.domain.system.SysResource;
import com.awesome.web.mapper.system.SysResourceMapper;
import com.github.pagehelper.PageHelper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author adam
 * @ClassName DepartmentTest
 * @Description
 * @create 2017/6/2 10:32
 */
public class ResourceTest extends AwesomeApplicationTests {
    @Autowired
    private SysResourceMapper sysResourceMapper;

    /**
     * 部门树测试
     */
    @Test
    public void testListBySearch(){

        PageHelper.offsetPage(0,10);
        List<Long> models = new ArrayList<>();

        models.add(1L);
        models.add(3L);
        models.add(7L);
        models.add(8L);
        models.add(4L);
        models.add(5L);
        models.add(6L);

        DataTableSearch search = new DataTableSearch();
        search.setSearchValue("");
        List<SysResource> resources = sysResourceMapper.listBySearch(new SysResource(),search,models);

        System.out.println("###################");

    }

}
