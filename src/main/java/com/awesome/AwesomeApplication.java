package com.awesome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement //开启事务管理 等同于 <tx:annotation-driven />
@EnableCaching               //开启ehcache缓存
public class AwesomeApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AwesomeApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AwesomeApplication.class);
	}
}
