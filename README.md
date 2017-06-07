# spring-boot 整合 mybatis <br/>
## 1、配置文件    <br/>
>config包
>>DruidDBConfig.java　　　配置druid线程池  <br/>
>>MybatisConfig.java　　　配置mybatis  （其中在mybatis-config.xml中集成了PageHelper分页插件）  <br/>
>>
>druid包　　用于配置druid监控<br/>
>>DruidStatViewServlet.java　　需要继承  StatViewServlet<br/>
>>DruidStatFilter.java　　　　　需要继承  WebStatFilter<br/>
>>
>security包  用于集成spring-security
>>WebSecurityConfig     <br/>
>>MyInvocationSecurityMetadataSourceService.java <br/>
>>MyAccessDecisionManager.java <br/>
>>MyUserDetailService.java  <br/>
>>MyFilterSecurityInterceptor.java   <br/>
>>LoginSuccessHandler.java   <br/>