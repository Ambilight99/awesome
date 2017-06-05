package com.awesome.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

/**
 * @author adam
 * @ClassName MyAccessDecisionManager
 * @Description 访问决策器
 * @create 2017/6/4 15:04
 */
/**  
  AccessdecisionManager在Spring security中是很重要的。  
    
  在验证部分简略提过了，所有的Authentication实现需要保存在一个GrantedAuthority对象数组中。   
  这就是赋予给主体的权限。 GrantedAuthority对象通过AuthenticationManager  
  保存到 Authentication对象里，然后从AccessDecisionManager读出来，进行授权判断。   
    
  Spring Security提供了一些拦截器，来控制对安全对象的访问权限，例如方法调用或web请求。   
  一个是否允许执行调用的预调用决定，是由AccessDecisionManager实现的。   
  这个 AccessDecisionManager 被AbstractSecurityInterceptor调用，  
  它用来作最终访问控制的决定。 这个AccessDecisionManager接口包含三个方法：   
    
  void decide(Authentication authentication, Object secureObject,  
     List<ConfigAttributeDefinition> config) throws AccessDeniedException;  
  boolean supports(ConfigAttribute attribute);  
  boolean supports(Class clazz);  
    
   从第一个方法可以看出来，AccessDecisionManager使用方法参数传递所有信息，这好像在认证评估时进行决定。   
   特别是，在真实的安全方法期望调用的时候，传递安全Object启用那些参数。   
   比如，让我们假设安全对象是一个MethodInvocation。   
   很容易为任何Customer参数查询MethodInvocation，  
   然后在AccessDecisionManager里实现一些有序的安全逻辑，来确认主体是否允许在那个客户上操作。   
   如果访问被拒绝，实现将抛出一个AccessDeniedException异常。  
   
   这个 supports(ConfigAttribute) 方法在启动的时候被  
   AbstractSecurityInterceptor调用，来决定AccessDecisionManager  
   是否可以执行传递ConfigAttribute。   
   supports(Class)方法被安全拦截器实现调用，  
   包含安全拦截器将显示的AccessDecisionManager支持安全对象的类型。  
  */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {
    /**
     * Resolves an access control decision for the passed parameters.
     *
     * @param authentication   the caller invoking the method (not null)
     * @param object           the secured object being called
     * @param configAttributes the configuration attributes associated with the secured
     *                         object being invoked
     * @throws AccessDeniedException               if access is denied as the authentication does not
     *                                             hold a required authority or ACL privilege
     * @throws InsufficientAuthenticationException if access is denied as the
     *                                             authentication does not provide a sufficient level of trust
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if(configAttributes == null){
            return;
        }
        Iterator<ConfigAttribute> iter = configAttributes.iterator();
        while(iter.hasNext()){
            ConfigAttribute ca = iter.next();
            String needRole = ((SecurityConfig) ca).getAttribute();
            //ga 为用户被赋予的权限，needRole为访问相应的权限。
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (Objects.equals(needRole.trim(), ga.getAuthority().trim())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足");
    }

    /**
     * Indicates whether this <code>AccessDecisionManager</code> is able to process
     * authorization requests presented with the passed <code>ConfigAttribute</code>.
     * <p>
     * This allows the <code>AbstractSecurityInterceptor</code> to check every
     * configuration attribute can be consumed by the configured
     * <code>AccessDecisionManager</code> and/or <code>RunAsManager</code> and/or
     * <code>AfterInvocationManager</code>.
     * </p>
     *
     * @param attribute a configuration attribute that has been configured against the
     *                  <code>AbstractSecurityInterceptor</code>
     * @return true if this <code>AccessDecisionManager</code> can support the passed
     * configuration attribute
     */
    @Override
    public boolean supports(ConfigAttribute attribute)
    {
        return true;
    }

    /**
     * Indicates whether the <code>AccessDecisionManager</code> implementation is able to
     * provide access control decisions for the indicated secured object type.
     *
     * @param clazz the class that is being queried
     * @return <code>true</code> if the implementation can process the indicated class
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
