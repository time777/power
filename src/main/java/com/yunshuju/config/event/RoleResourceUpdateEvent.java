package com.yunshuju.config.event;

import org.springframework.context.ApplicationEvent;

import com.yunshuju.domain.entity.SysRoleResource;

/**
 * 功能：角色资源菜单关联表更新事件
 *
 * @author： YuYanHui(yuyh@dtdream.com)
 * @create： 2018-09-07 11:24:06
 * @version： 2018 Version 1.0
 * @company： 数梦工场 Created with IntelliJ IDEA
 */
public class RoleResourceUpdateEvent extends ApplicationEvent {

	private Action action;

	public RoleResourceUpdateEvent(SysRoleResource sysRoleResource) {
		super(sysRoleResource);
	}

	public RoleResourceUpdateEvent(SysRoleResource sysRoleResource, Action action) {
		super(sysRoleResource);
		this.action = action;
	}

	@Override
	public SysRoleResource getSource() {
		return (SysRoleResource) super.source;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public enum Action {
		/**
		 * 添加资源
		 */
		ADD,
		/**
		 * 修改资源
		 */
		UPDATE,
		/**
		 * 删除资源
		 */
		DELETE
	}

}
