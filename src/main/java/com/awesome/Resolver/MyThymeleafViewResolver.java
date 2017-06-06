package com.awesome.Resolver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.View;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

/**
 * @author adam
 * @ClassName MyThymeleafViewResolver
 * @Description
 * @create 2017/6/6 16:04
 */
public class MyThymeleafViewResolver extends ThymeleafViewResolver {

    @Value("${spring.thymeleaf.prefix}")
    private String prefix;
    @Value("${spring.thymeleaf.suffix}")
    private String suffix;

    /**
     * 如果视图存在，则加载视图； 如果不存在，寻找其他视图解析器
     * @param viewName
     * @param locale
     * @return
     * @throws Exception
     */
    @Override
    protected View loadView(final String viewName, final Locale locale) throws Exception {
        String resourceName=prefix + viewName + suffix;
        try {
            //判断视图文件是否存在，如果不存在返回null
            this.getApplicationContext().getResource(resourceName).getInputStream();
        }catch(final IOException e){
            if (logger.isDebugEnabled()) {
                if (logger.isTraceEnabled()) {
                    logger.trace("视图名："+resourceName+"{}不存在！");
                }else{
                    logger.debug("视图名："+resourceName+"{}不存在！");
                }
            }
            return null;
        }

        return super.loadView(viewName,locale);

    }
}
