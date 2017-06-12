package com.awesome.security.login;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * @author adam
 * @ClassName LoginFailHandler
 * @Description 登录失败处理
 * @create 2017/6/12 14:09
 */
@Component
public class LoginFailHandler implements AuthenticationFailureHandler{
    /**
     * Called when an authentication attempt fails.
     *
     * @param request   the request during which the authentication attempt occurred.
     * @param response  the response.
     * @param exception the exception which was thrown to reject the authentication
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//        用户名不存在:UsernameNotFoundException;     //需要自定义一个authentication provider，替换掉默认的DaoAuthenticationProvider.  设置hideUserNotFoundExceptions为false
//        密码错误:BadCredentialException;
//        帐户被锁:LockedException;
//        帐户未启动:DisabledException;
//        密码过期:CredentialExpiredException;等等!
//        System.out.println(exception.getLocalizedMessage());

        String username = request.getParameter("username");
        response.sendRedirect("login?error=authFailure&username="+username);
    }
}
