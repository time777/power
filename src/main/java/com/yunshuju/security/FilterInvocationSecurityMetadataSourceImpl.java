package com.yunshuju.security;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.yunshuju.config.event.RoleResourceUpdateEvent;
import com.yunshuju.domain.entity.SysResource;
import com.yunshuju.domain.entity.SysRole;
import com.yunshuju.domain.entity.SysRoleResource;
import com.yunshuju.service.SecurityService;

/**
 * 功能：资源角色授权器
 * 处理资源与角色之间的关系：系统在启动的时候就要加载资源与角色的数据
 *
 * @author： YuYanHui(yuyh@dtdream.com)
 * @create： 2018-09-17 11:24:06
 * @version： 2018 Version 1.0
 * @company： 数梦工场 Created with IntelliJ IDEA
 */
@Component
public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {

	private static final Logger logger = LoggerFactory.getLogger(FilterInvocationSecurityMetadataSourceImpl.class);

	@Autowired
	private SecurityService securityService;

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;


	/**
	 * 加载权限和资源，初始化权限和资源变量
	 *
	 * @PostConstruct:
	 * 在项目启动的时候即在spring容器启动的时候执行，可作为一些数据的常规化加载，比如数据字典之类
	 */
	@PostConstruct
	private void loadResourceDefine() {
		logger.info("开始初始化系统权限和资源...");
		//在Web服务器启动时，提取系统中的所有权限
		List<SysRole> sysRoleList = securityService.findAllRole();
	   /*
		 * 应当是资源为key，权限为value。资源通常为url，权限就是那些以ROLE_为前缀的角色。
         * 一个资源可以由多个权限来访问。
         */
		resourceMap = new HashMap<>();
		for (SysRole sysRole : sysRoleList) {
			//将角色名封装一个安全配置 说白了就是一个角色名，只是为了和框架整合换一种说法
			ConfigAttribute configAttribute = new SecurityConfig(sysRole.getName());
			List<String> urlList = new ArrayList<String>();
			List<SysResource> sysResourceList = securityService.findPermResourceByRoleId(sysRole.getId());
			//如果不加判断，这里如果 findByRoleId(roleId);为null.则会报错
			if (CollectionUtils.isNotEmpty(sysResourceList)) {
				for (SysResource sysResource : sysResourceList) {
					urlList.add(sysResource.getApi());
 				}
			}
			for (String res : urlList) {
				String url = res;
				/*
                 * 判断资源文件和权限的对应关系，如果已经存在相关的资源url，
                 * 则要通过该url为key提取出权限集合，将权限增加到权限集合中。
                 */
				if (resourceMap.containsKey(url)) {
					Collection<ConfigAttribute> value = resourceMap.get(url);
					value.add(configAttribute);
					resourceMap.put(url, value);
				} else {
					Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
					atts.add(configAttribute);
					//一个URL对应多种角色
					resourceMap.put(url, atts);
				}
			}
		}
		logger.info("系统权限和资源初始化完成...");
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		if (resourceMap == null) {
			loadResourceDefine();
		}
		HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
		AntPathRequestMatcher matcher;
		String resUrl;
		for (Iterator<String> iter = resourceMap.keySet().iterator(); iter.hasNext(); ) {
			resUrl = iter.next();
			matcher = new AntPathRequestMatcher(resUrl);
			if (matcher.matches(request)) {
				return resourceMap.get(resUrl);
			}
		}
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
//		//根据DefaultFilterInvocationSecurityMetadataSource写的
//		Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();
//		for (Map.Entry<String, Collection<ConfigAttribute>> entry : resourceMap.entrySet()) {
//			allAttributes.addAll(entry.getValue());
//		}
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}


	/**
	 * 初始化加载的话，在角色和资源更新的话数据就不完整了，需要用spring监听器监听角色资源表的操作
	 *
	 * @param event
	 */
	public void updateRoleResource(RoleResourceUpdateEvent event) {
		SysRoleResource sysRoleResource = event.getSource();
		synchronized (this) {
			switch (event.getAction()) {
				case ADD:
					resourceMap = null;
					break;
				case UPDATE:
					break;
				case DELETE:
					resourceMap = null;
					break;
				default:
			}
		}
	}


}
