package com.yunshuju.common.mybatis;

import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.yunshuju.common.utils.ApplicationContextUtils;
import com.yunshuju.common.utils.DateUtils;
import com.yunshuju.common.utils.ids.IdGenerateFactory;
import com.yunshuju.security.SecurityContextUtils;

/**
 * Mybatis拦截
 */
public class CommonMateObjectHandler extends MetaObjectHandler {

	private IdGenerateFactory idGenerateFactory;

	@Override
	public void insertFill(MetaObject metaObject) {
		if (StringUtils.isEmpty(getFieldValByName("id", metaObject))) {
			setFieldValByName("id", getIdGenerateFactory().nextStringId(), metaObject);
		}
		String currentUserId = getCurrentUserId();
		Date now = DateUtils.now();
		setFieldValByName("creator", currentUserId, metaObject);
		setFieldValByName("created", now, metaObject);
		setFieldValByName("updater", currentUserId, metaObject);
		setFieldValByName("updated", now, metaObject);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		setFieldValByName("updater", getCurrentUserId(), metaObject);
		setFieldValByName("updated", DateUtils.now(), metaObject);
	}

	private String getCurrentUserId() {
		String currentUserId;
		try {
			currentUserId = SecurityContextUtils.getCurrentUserId();
		} catch (Exception e) {
			//在没有登录的情况下调用ShiroContextUtils.getCurrentUserId()会发生异常，例如任务调度执行的sql
			currentUserId = "auto";
		}
		return currentUserId == null ? "auto" : currentUserId;
	}

	private IdGenerateFactory getIdGenerateFactory() {
		if (idGenerateFactory == null) {
			idGenerateFactory = ApplicationContextUtils.getBean(IdGenerateFactory.class);
		}
		return idGenerateFactory;
	}

}