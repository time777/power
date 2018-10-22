package com.yunshuju.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.yunshuju.domain.entity.SysResource;
import com.yunshuju.domain.entity.SysRole;
import com.yunshuju.domain.entity.SysRoleResource;
import com.yunshuju.domain.entity.SysUser;
import com.yunshuju.domain.entity.SysUserRole;
import com.yunshuju.service.SecurityService;
import com.yunshuju.service.SysResourceService;
import com.yunshuju.service.SysRoleResourceService;
import com.yunshuju.service.SysRoleService;
import com.yunshuju.service.SysUserRoleService;
import com.yunshuju.service.SysUserService;

/**
 * 功能：
 *
 * @author： YuYanHui(yuyh@dtdream.com)
 * @create： 2018-09-18 16:55:31
 * @version： 2018 Version 1.0
 * @company： 数梦工场 Created with IntelliJ IDEA
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SecurityServiceImpl implements SecurityService {

	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private SysUserRoleService sysUserRoleService;
	@Resource
	private SysResourceService sysResourceService;
	@Resource
	private SysRoleResourceService sysRoleResourceService;


	@Override
	public SysUser findUserByUsername(String username) {
		return sysUserService.findByUsername(username);
	}

	@Override
	public SysUser findUserByUsernameAndPassword(String username, String password) {
		return sysUserService.findByUsernameAndPassword(username, password);
	}

	@Override
	public Set<SysRole> findRolesByUserId(String userId) {
		Set<SysRole> sysRoleSet = new HashSet<>();
		List<SysUserRole> sysUserRoleList = sysUserRoleService.selectList(new EntityWrapper<SysUserRole>().eq("user_id", userId));
		if (CollectionUtils.isNotEmpty(sysUserRoleList)) {
			for (SysUserRole sysUserRole : sysUserRoleList) {
				sysRoleSet.add(sysRoleService.selectOne(new EntityWrapper<SysRole>().eq("id", sysUserRole.getRoleId())));
			}
		}
		return sysRoleSet;
	}

	@Override
	public List<SysResource> findAllPermResource() {
		return sysResourceService.selectList(new EntityWrapper<>());
	}

	@Override
	public Set<String> findAllRoleName() {
		Set<String> roleNames = new HashSet<>();
		List<SysRole> sysRoleList = sysRoleService.selectList(new EntityWrapper<>());
		if (CollectionUtils.isNotEmpty(sysRoleList)) {
			for (SysRole sysRole : sysRoleList) {
				roleNames.add(sysRole.getName());
			}
		}
		return roleNames;
	}

	@Override
	public List<SysRole> findAllRole() {
		return sysRoleService.selectList(new EntityWrapper<>());
	}


//	@Override
//	public String findRoleIdByUserId(String userId) {
//		SysRole sysRole = sysUserService.findRoleById(SecurityContextUtils.getCurrentUser().getId());
//		return sysRole.getId();
//	}

	@Override
	public List<SysResource> findPermResourceByRoleId(String roleId) {
		List<SysRoleResource> sysRoleResourceList = sysRoleResourceService.selectList(new EntityWrapper<SysRoleResource>().eq("role_id", roleId));
		List<SysResource> sysResourceList = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(sysRoleResourceList)) {
			for (SysRoleResource sysRoleResource : sysRoleResourceList) {
				SysResource sysResource = sysResourceService.selectById(sysRoleResource.getResourceId());
				sysResourceList.add(sysResource);
			}
		}
		return sysResourceList;
	}

	@Override
	public SysResource findResourceById(String id) {
		return sysResourceService.selectById(id);
	}

	@Override
	public SysUser login(String userName, String password, boolean rememberMe) {
		return null;
	}

}
