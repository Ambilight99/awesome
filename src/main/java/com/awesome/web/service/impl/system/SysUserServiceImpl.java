package com.awesome.web.service.impl.system;

import com.awesome.util.Md5SaltUtil;
import com.awesome.web.domain.common.ResultMessage;
import com.awesome.web.domain.common.datatable.DataTableSearch;
import com.awesome.web.domain.system.SysDepartment;
import com.awesome.web.domain.system.SysUser;
import com.awesome.web.domain.system.SysUserRole;
import com.awesome.web.mapper.system.SysDepartmentMapper;
import com.awesome.web.mapper.system.SysUserMapper;
import com.awesome.web.service.system.SysUserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author adam
 * @ClassName UserServiceImpl
 * @Description
 * @create 2017/6/2 11:54
 */
@Service
@CacheConfig(cacheNames = "DEFAULT_CACHE")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysDepartmentMapper sysDepartmentMapper;


    /**
     * 列出所有用户信息
     *
     * @param user
     * @param search
     * @return
     */
    @Override
    public List<SysUser> list(SysUser user, DataTableSearch search) {
        PageHelper.offsetPage(search.getStart(),search.getLength());
        return sysUserMapper.listByUser(user,search);
    }

    /**
     * 根据条件列出所有用户信息
     *
     * @param user
     * @param search
     * @param subdivision
     * @return
     */
    @Override
    public List<SysUser> list(SysUser user, DataTableSearch search, boolean subdivision) {
        List<Long> departments = new ArrayList<>();
        if(subdivision){
            departments = this.getAllChildren(user.getDepartment(), sysDepartmentMapper.list() );
        }else{
            departments.add( user.getDepartment() );
        }
        PageHelper.offsetPage(search.getStart(),search.getLength());
        return sysUserMapper.listBySearch(user,search,departments);
    }

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    @Override
    public SysUser findById(Long id) {
        return sysUserMapper.findById(id);
    }

    /**
     * 保存或更新用户
     *
     * @param user
     * @return
     */
    @Override
    public int saveOrUpdate(SysUser user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        user.setPassword( Md5SaltUtil.getEncryptedPwd(user.getPassword()) );
        if(user.getId()==null){
            return sysUserMapper.insert(user);
        }else{
            return sysUserMapper.update(user);
        }
    }

    /**
     * 用户-角色关系保存
     *
     * @param roles
     * @param userId
     * @return
     */
    @Override
    public int roleSave(Long[] roles, Long userId) {
        sysUserMapper.deleteUserRoleById(userId);
        Set<Long> roleSet = new HashSet<>(Arrays.asList(roles));
        List<SysUserRole> list = new ArrayList<>();
        roleSet.forEach(x-> {
            list.add(new SysUserRole(userId, x));
        });

        if(!list.isEmpty()){
            return sysUserMapper.insertUserRole(list);
        }
        return 0;
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @Override
    public int deleteById(Long id) {
        if(id==null){
            return 0;
        }
        SysUser user = new SysUser();
        user.setId(id);
        user.setStatus(0);
        return sysUserMapper.update(user);
    }

    /**
     * 递归获取所有的子节点
     * @param id
     * @param departments
     * @return
     */
    private List<Long> getAllChildren(Long id,List<SysDepartment> departments){
        List<Long> ids = new ArrayList<>();
        ids.add(id);
        return this.getAllChildren(ids,departments);
    }

    /**
     * 递归获取所有的子节点
     * @param ids
     * @param departments
     * @return
     */
    private List<Long> getAllChildren(List<Long> ids,List<SysDepartment> departments){
        List<Long> childrenIds = new ArrayList<>();
        if(ids.isEmpty()){
            return childrenIds;
        }
        for( Long id : ids){
            for( SysDepartment department : departments ){
                if( Objects.equals(id, department.getParent()) ){
                    childrenIds.add(department.getId());
                }
            }
        }
        ids.addAll( getAllChildren(childrenIds,departments) );
        return ids;
    }

}
