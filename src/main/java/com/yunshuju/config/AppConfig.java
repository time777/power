package com.yunshuju.config;

import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.yunshuju.common.utils.DueBooleanStringSerializer;
import com.yunshuju.common.utils.date.DateUtil;
import com.yunshuju.common.utils.ids.IdGenerateFactory;

/**
 * 功能：id和JSON序列化注入
 *
 * @author： YuYanHui(yuyh@dtdream.com)
 * @create： 2018-09-07 11:24:06
 * @version： 2018 Version 1.0
 * @company： 数梦工场 Created with IntelliJ IDEA
 */
@Configuration
public class AppConfig {
    @Bean
    public IdGenerateFactory idGenerateFactory() {
        return new IdGenerateFactory(1L);
    }

    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder(){
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(Include.NON_NULL);
        builder.dateFormat(new SimpleDateFormat(DateUtil.FORMAT_DATETIME));
        //Long、Double类型到前台转为String即加上双引号
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        simpleModule.addSerializer(Float.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Float.TYPE, ToStringSerializer.instance);
        simpleModule.addSerializer(String.class, DueBooleanStringSerializer.instance);
        builder.modulesToInstall(simpleModule);
        return builder;
    }

}
