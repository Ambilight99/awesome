package com.awesome.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import java.io.IOException;

/**
 * @author adam
 * @ClassName MyFilterSecurityInterceptor
 * @Description
 * @create 2017/6/4 15:16
 */
@Component
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    @Autowired
    private MyInvocationSecurityMetadataSourceService myInvocationSecurityMetadataSourceService;
    @Autowired
    private MyAccessDecisionManager myAccessDecisionManager;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostConstruct
    public void init() {
        System.out.println("###################");
        super.setAuthenticationManager(authenticationManager);
        super.setAccessDecisionManager(myAccessDecisionManager);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        FilterInvocation filterInvocation = new FilterInvocation(servletRequest,servletResponse,filterChain);
        invoke(filterInvocation);
    }

    /**
     * 里面会调用 myAccessDecisionManager 进行权限校验
     * @param filterInvocation
     */
    public void invoke(FilterInvocation filterInvocation) {
        //filterInvocation里面有一个被拦截的url
        //里面调用MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
        //再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
        InterceptorStatusToken token = super.beforeInvocation(filterInvocation);
        try {
            filterInvocation.getChain().doFilter(filterInvocation.getRequest(), filterInvocation.getResponse());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }finally {
            super.afterInvocation(token,null);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter -------------------init");
    }

    @Override
    public void destroy() {
        System.out.println("filter --------------------destroy");
    }

    /**
     * Indicates the type of secure objects the subclass will be presenting to the
     * abstract parent for processing. This is used to ensure collaborators wired to the
     * {@code AbstractSecurityInterceptor} all support the indicated secure object class.
     *
     * @return the type of secure object the subclass provides services for
     */
    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.myInvocationSecurityMetadataSourceService;
    }
}
