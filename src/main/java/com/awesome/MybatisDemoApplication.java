package com.awesome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement //开启事务管理 等同于 <tx:annotation-driven />
@SpringBootApplication
public class MybatisDemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MybatisDemoApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MybatisDemoApplication.class);
	}
}
