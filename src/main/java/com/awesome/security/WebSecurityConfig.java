package com.awesome.security;

import com.awesome.security.login.LoginFailHandler;
import com.awesome.security.login.LoginSuccessHandler;
import com.awesome.util.Md5SaltUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;
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
    private LoginFailHandler loginFailHandler;
    @Autowired
    private MyUserDetailService myUserDetailsService;
    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //以下两行代码，取消csrf验证 解决Spring Boot不允许加载iframe的问题
        http.headers().frameOptions().disable();
        http.csrf().disable();

        http//.addFilterAfter(myFilterSecurityInterceptor, FilterSecurityInterceptor.class)//在正确的位置添加我们自定义的过滤器  
            .authorizeRequests()
                .antMatchers("/home","/login","/exception/**").permitAll()//访问：/home 无需登录认证权限
                .antMatchers("/static/**").permitAll()
                .anyRequest().authenticated() //其他所有资源都需要认证，登陆后访问
                .and()
            .formLogin()
                .loginPage("/login")//指定登录页是”/login”
                .permitAll()
                .successHandler( loginSuccessHandler ) //登录成功后可使用loginSuccessHandler存储用户信息，可选。
                .failureHandler( loginFailHandler )     //登录失败处理
                .and()
            .logout()
                .logoutSuccessUrl("/login") //退出登录后的默认网址是”/login”
                .permitAll()
                .invalidateHttpSession(true)
                .and()
            .exceptionHandling()
                .accessDeniedPage("/exception/403")
                .and()
            .rememberMe()   //登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表
                .tokenValiditySeconds( 7*24*60*60 ) //7天自动登录
                .tokenRepository(tokenRepository() );//指定记住登录信息所使用的数据源
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
                if(Objects.equals(encodedPassword, "userNotFoundPassword")){
                    return false;
                }
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


    @Autowired
    private DataSource dataSource;

    /**
     * 记住我功能使用的数据源
     * spring security 内部都写死了，这里要把 这个DAO 注入
     * @return
     */
    @Bean
    public JdbcTokenRepositoryImpl tokenRepository(){
        JdbcTokenRepositoryImpl j=new JdbcTokenRepositoryImpl();
        j.setDataSource(dataSource);
        return j;
    }
}
