package com.yunshuju.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtils implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	public static <T> T getBean(Class<T> cls) {
		return applicationContext.getBean(cls);
	}

	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextUtils.applicationContext = applicationContext;
	}
}
