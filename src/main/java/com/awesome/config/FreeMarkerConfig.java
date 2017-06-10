package com.awesome.config;

import freemarker.template.TemplateModelException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author adam
 * @ClassName FreeMarkerConfig
 * @Description 在freemarker中添加全局变量
 * @create 2017/6/8 10:16
 */
@Configuration
public class FreeMarkerConfig implements ApplicationContextAware {
    private Logger logger = LoggerFactory.getLogger(FreeMarkerConfig.class);
    //上下文环境
    private ApplicationContext applicationContext;
    @Autowired
    protected freemarker.template.Configuration configuration;

    @PostConstruct
    public void  setSharedVariable() {
        logger.info("加载freemarker自定义全局变量！");
        String contextPath = applicationContext.getApplicationName();
        try {
            configuration.setSharedVariable("root",contextPath);
        } catch (TemplateModelException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
