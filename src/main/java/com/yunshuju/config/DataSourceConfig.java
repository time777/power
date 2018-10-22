package com.yunshuju.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 功能：数据库注入
 *
 * @author： YuYanHui(yuyh@dtdream.com)
 * @create： 2018-09-07 11:24:06
 * @version： 2018 Version 1.0
 * @company： 数梦工场 Created with IntelliJ IDEA
 */
@Configuration
public class DataSourceConfig {

	@Bean
	@ConfigurationProperties("app.datasource")
	public DataSource dataSource() {
		return new DruidDataSource();
	}
}
