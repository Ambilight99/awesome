package com.awesome.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author adam
 * @ClassName MybatisConfig
 * @Description
 * @create 2017/5/31 19:28
 */
@Configuration
@AutoConfigureAfter(DruidDBConfig.class)
@EnableTransactionManagement
public class MybatisConfig implements TransactionManagementConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(MybatisConfig.class);

    @Value("${mybatis.type-aliases-package}")
    private String typeAliasesPackage;  //配置类型别名

    @Value("${mybatis.type-aliases-package}")
    private String mapperLocations;     //配置mapper的扫描，找到所有的mapper.xml映射文件

    @Value("${mybatis.config-location}")
    private String configLocation;       //  加载全局的配置文件

    @Autowired
    private DataSource dataSource;

    @Bean(name="sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean(){
        try {
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            //设置数据池
            sqlSessionFactoryBean.setDataSource(dataSource);
            // 读取配置
            sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources(mapperLocations);
            sqlSessionFactoryBean.setMapperLocations(resources);

            sqlSessionFactoryBean.setConfigLocation( new DefaultResourceLoader().getResource(configLocation));

            return sqlSessionFactoryBean.getObject();
        } catch (IOException e) {
            logger.warn("mybatis resolver mapper*xml is error");
            return null;
        } catch (Exception e) {
            logger.warn("mybatis sqlSessionFactoryBean create error");
            return null;
        }


    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }


}
