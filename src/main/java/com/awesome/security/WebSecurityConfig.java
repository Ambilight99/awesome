package com.awesome.security;

import com.awesome.util.Md5SaltUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * @author adam
 * @ClassName WebSecurityConfig  spring-security配置
 * @Description
 * @create 2017/6/3 14:29
 */
@Configuration
@EnableWebSecurity			 //开启webSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Autowired
    private MyUserDetailService myUserDetailsService;
    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    //http://localhost:8080/login 输入正确的用户名密码 并且选中remember-me 则登陆成功，转到 index页面
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class)//在正确的位置添加我们自定义的过滤器  
            .authorizeRequests()
                .antMatchers("/home","/resources/**").permitAll()//访问：/home 无需登录认证权限
                .anyRequest().authenticated() //其他所有资源都需要认证，登陆后访问
                .and()
            .formLogin()
                .loginPage("/login")//指定登录页是”/login”
                .permitAll()
                .successHandler( loginSuccessHandler ) //登录成功后可使用loginSuccessHandler存储用户信息，可选。
                .and()
            .logout()
                .logoutSuccessUrl("/home") //退出登录后的默认网址是”/home”
                .permitAll()
                .invalidateHttpSession(true)
                .and()
            .rememberMe()//登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表
            .tokenValiditySeconds(1209600);
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
       return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //需要将密码加密后写入数据库
        auth.userDetailsService( myUserDetailsService )
                .passwordEncoder( md5PasswordEncoder() );   //指定密码加密所使用的加密器为Md5PasswordEncoder
        auth.eraseCredentials(false);
    }

    /**
     *  指定密码加密所使用的加密器为Md5PasswordEncoder
     * @return
     */
    @Bean
    public PasswordEncoder md5PasswordEncoder(){
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return (String) rawPassword;
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                boolean matche = false;
                try {
                    matche = Md5SaltUtil.validPassword( (String)rawPassword,encodedPassword);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return matche;
            }
        };
    }

    /**
     *  指定密码加密所使用的加密器为BCryptPasswordEncoder
     * @returnS
     */
    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder(4);
    }
}
