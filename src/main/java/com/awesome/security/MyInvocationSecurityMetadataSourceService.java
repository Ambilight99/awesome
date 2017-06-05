package com.awesome.security;

import com.awesome.web.domain.system.SysResource;
import com.awesome.web.domain.system.SysRole;
import com.awesome.web.mapper.system.SysResourceMapper;
import com.awesome.web.mapper.system.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author adam
 * @ClassName MyInvocationSecurityMetadataSourceService
 * @Description  容器启动(MyInvocationSecurityMetadataSource： loadResourceDefine加载系统资源与权限列表)
 * @create 2017/6/4 14:00
 */
@Component
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource{

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysResourceMapper sysResourceMapper;

    /**
     * 应当是资源为key， 权限为value。 资源通常为url， 权限就是那些以ROLE_为前缀的角色。 一个资源可以由多个权限来访问。 
     */
    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    /**
     * 加载资源信息
     * 被@PostConstruct修饰的方法会在服务器加载Servle的时候运行，并且只会被服务器执行一次。PostConstruct在构造函数之后执行,init()方法之前执行。
     */
    @PostConstruct
    private void loadResourceDefine(){
        System.out.println("+++++++++++++++loadResourceDefine+++++++++++");
        resourceMap = new HashMap<>();
        List<SysRole> roles =sysRoleMapper.listAll();
        for(SysRole role : roles){
            String roleName = role.getName();
            ConfigAttribute ca = new SecurityConfig(roleName); //把权限放入ConfigAttribute
            List<SysResource> resources = sysResourceMapper.listByRoleName(roleName);
            for(SysResource resource : resources){
                String url = resource.getUrl();
                //判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，将权限增加到权限集合中。 
                if(resourceMap.containsKey(url)){
                    Collection<ConfigAttribute> value = resourceMap.get(url);
                    value.add(ca);
                    resourceMap.put(url,value);
                }else{
                    Collection<ConfigAttribute> attr =new ArrayList<>();
                    attr.add(ca);
                    resourceMap.put(url,attr);
                }
            }

        }
    }

    /**
     * Accesses the {@code ConfigAttribute}s that apply to a given secure object.
     *
     * @param object the object being secured
     * @return the attributes that apply to the passed in secured object. Should return an
     * empty collection if there are no applicable attributes.
     * @throws IllegalArgumentException if the passed object is not of a type supported by
     *                                  the <code>SecurityMetadataSource</code> implementation
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // object 是一个URL，被用户请求的url。
        FilterInvocation filterInvocation = (FilterInvocation) object;
        if(resourceMap == null){
            loadResourceDefine();
        }
        Iterator<String> iter = resourceMap.keySet().iterator();
        while(iter.hasNext()){
            String resURL = iter.next();
            RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
            if (requestMatcher.matches(filterInvocation.getHttpRequest())) {
                return resourceMap.get(resURL);
            }
        }
        return null;

    }

    /**
     * If available, returns all of the {@code ConfigAttribute}s defined by the
     * implementing class.
     * <p>
     * This is used by the {@link AbstractSecurityInterceptor} to perform startup time
     * validation of each {@code ConfigAttribute} configured against it.
     *
     * @return the {@code ConfigAttribute}s or {@code null} if unsupported
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return new ArrayList<>();
    }

    /**
     * Indicates whether the {@code SecurityMetadataSource} implementation is able to
     * provide {@code ConfigAttribute}s for the indicated secure object type.
     *
     * @param clazz the class that is being queried
     * @return true if the implementation can process the indicated class
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
