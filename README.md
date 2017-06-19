# spring-boot 整合spring-security mybatis ( freemarker/thymeleaf)，前台用到了ztree，datatable，zui <br/>

### 项目开发环境IEDA，  jdk8（使用低于此版本的，需要修改项目中lambda表达式），sql文件在resources/sql目录下面 <br/>

### 详细的文档还在整理中。。



## spring Secrity 流程说明
springSecurity执行流程
	
整个程序执行的过程如下：

1、容器启动时
   通过MyInvocationSecurityMetadataSource # loadResourceDefine 加载系统资源与权限列表resourceMap(一个Map结构，资源[url]为key，权限[auth]为value )
   
2、WEB服务器启动，加载security内置的过滤器链，
   HttpSessionContextIntegrationFilter 
   LogoutFilter
   AuthenticationProcessingFilter
   DefaultLoginPageGeneratingFilter
   BasicProcessingFilter
   SecurityContextHolderAwareRequestFilter
   RememberMeProcessingFilter
   AnonymousProcessingFilter
   ExceptionTranslationFilter
   SessionFixationProtectionFilter
   FilterSecurityInterception
 
   自定义过滤器拦截请求 MyFilterSecurityInterceptor ，并设置在FilterSecurityInterception之前拦截。
   给自定义过滤器，注入参数（MyInvocationSecurityMetadataSource,MyAccessDecisionManager，MyUserDetailService）

3、用户登陆
   通过 AuthenticationManager 对用户输入的用户名和密码，然后根据用户定义的密码算法和盐值等进行计算并和数据库比对。
   
4、如果验证成功 
   通过MyUserDetailsService # loadUserByUsername
   生成UserDetails供Spring Security使用。
   
5、当用户点击某个功能（URL）时。
   通过MyAccessDecisionManager # decide
   根据URl取得 系统资源与权限列表 resourceMap 中的权限集合。与登录用户的权限集合进行循环对比，如果存在相同权限，代表用户具有访问该资源的权利。

http://blog.csdn.net/qq_15042899/article/details/73467049






##  包说明   <br/>
* config包
  * DruidDBConfig.java　　　配置druid线程池  <br/>
  * MybatisConfig.java　　　配置mybatis  （其中在mybatis-config.xml中集成了PageHelper分页插件）  <br/>
  * MvcConfig.java　　　　　配置springMVC <br/>
* druid包　　用于配置druid监控<br/>
  * DruidStatViewServlet.java　　需要继承  StatViewServlet<br/>
  * DruidStatFilter.java　　　　　需要继承  WebStatFilter<br/>
* resolve包
  * MyThymeleafViewResolver.java　　　自定义的视图解析器，用于设置thymeleafViewResolve的优先级高于freemarkerViewResolver <br/>
* security包  用于集成spring-security
  * WebSecurityConfig.java <br/>
  * MyInvocationSecurityMetadataSourceService.java <br/>
  * MyAccessDecisionManager.java <br/>
  * MyUserDetailService.java  <br/>
  * MyFilterSecurityInterceptor.java   <br/>
  * LoginSuccessHandler.java   <br/>

 <br/>


## 配置中的注意事项
### 1、springboot下面thymeleaf和freemarker两种共存，并且设置thymeleaf的优先级高于freemarker<br/>     [http://blog.csdn.net/qq_15042899/article/details/72885889](http://blog.csdn.net/qq_15042899/article/details/72885889)

### 2、springsecurity配置记住我功能<br/>
```
@Configuration
@EnableWebSecurity			 //开启webSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {
、、、此处省略代码
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            、、、此处省略代码
            .rememberMe()   //登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表
            .tokenValiditySeconds( 7*24*60*60 ) //7天自动登录
            .tokenRepository(tokenRepository() );//指定记住登录信息所使用的数据源
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
```
