package com.awesome.config;

import com.awesome.resolver.MyThymeleafViewResolver;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

/**
 * @author adam
 * @ClassName MvcConfig
 * @Description
 * @create 2017/6/6 11:35
 */
@Configuration
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {


    @Value("${spring.thymeleaf.template-resolver-order}")
    private int thymeleafTemplateResolverOrder;

    @Value("${spring.freemarker.template-resolver-order}")
    private int freemarkerTemplateResolverOrder;

    @Autowired
    private FreeMarkerViewResolver freeMarkerViewResolver;

    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;

    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolverBean(){
        freeMarkerViewResolver.setOrder(freemarkerTemplateResolverOrder);
        return freeMarkerViewResolver;
    }

    @Bean
    public ThymeleafViewResolver  thymeleafViewResolverBean(){
        MyThymeleafViewResolver thymeleafViewResolver2 = new MyThymeleafViewResolver();
        BeanUtils.copyProperties(thymeleafViewResolver,thymeleafViewResolver2);
        thymeleafViewResolver2.setOrder( thymeleafTemplateResolverOrder ); //设置视图解析器的优先级
        return thymeleafViewResolver2;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/exception/403").setViewName("/exception/403");
    }

    /**
     * 对静态资源不处理，直接访问
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
}
