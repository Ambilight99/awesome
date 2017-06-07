# spring-boot 整合 mybatis <br/>
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
 * WebSecurityConfig     <br/>
 * MyInvocationSecurityMetadataSourceService.java <br/>
 * MyAccessDecisionManager.java <br/>
 * MyUserDetailService.java  <br/>
 * MyFilterSecurityInterceptor.java   <br/>
 * LoginSuccessHandler.java   <br/>




##配置中的注意事项
###1、springboot下面thymeleaf和freemarker两种共存，并且设置thymeleaf的优先级高于freemarker<br/>     [http://blog.csdn.net/qq_15042899/article/details/72885889](http://blog.csdn.net/qq_15042899/article/details/72885889)

###2、springsecurity配置记住我功能<br/>
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
