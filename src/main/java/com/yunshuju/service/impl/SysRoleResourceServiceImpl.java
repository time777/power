package com.yunshuju.service.impl;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunshuju.config.event.RoleResourceUpdateEvent;
import com.yunshuju.domain.dao.SysRoleResourceDao;
import com.yunshuju.domain.entity.SysRoleResource;
import com.yunshuju.service.BaseServiceImpl;
import com.yunshuju.service.SysRoleResourceService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YuYanHui
 * @since 2018-09-06
 */
@Service
public class SysRoleResourceServiceImpl extends BaseServiceImpl<SysRoleResourceDao, SysRoleResource> implements SysRoleResourceService, ApplicationEventPublisherAware {

	private ApplicationEventPublisher applicationEventPublisher;


	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean add(SysRoleResource sysRoleResource) {
		boolean result = insert(sysRoleResource);
		applicationEventPublisher.publishEvent(new RoleResourceUpdateEvent(sysRoleResource, RoleResourceUpdateEvent.Action.ADD));
		return result;
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean delete(String id) {
		SysRoleResource sysRoleResource = selectById(id);
		applicationEventPublisher.publishEvent(new RoleResourceUpdateEvent(sysRoleResource, RoleResourceUpdateEvent.Action.DELETE));
		boolean result = deleteById(id);
		return result;
	}


}
