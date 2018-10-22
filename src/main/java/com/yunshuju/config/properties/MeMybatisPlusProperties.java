package com.yunshuju.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.baomidou.mybatisplus.spring.boot.starter.MybatisPlusProperties;

/**
 * 功能：实际没有用到，只是为了在application.properties中有提示
 *
 * @author： YuYanHui(yuyh@dtdream.com)
 * @create： 2018-09-07 11:24:06
 * @version： 2018 Version 1.0
 * @company： 数梦工场 Created with IntelliJ IDEA
 */
@ConfigurationProperties("mybatis-plus")
public class MeMybatisPlusProperties extends MybatisPlusProperties {
}
