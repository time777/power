package com.yunshuju.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.yunshuju.common.utils.MD5Util;
import com.yunshuju.common.utils.exception.UsernameAlreadyExistException;
import com.yunshuju.common.utils.ids.IdGenerateFactory;
import com.yunshuju.domain.dao.SysUserDao;
import com.yunshuju.domain.entity.SysRole;
import com.yunshuju.domain.entity.SysUser;
import com.yunshuju.domain.entity.SysUserRole;
import com.yunshuju.service.BaseServiceImpl;
import com.yunshuju.service.SysRoleService;
import com.yunshuju.service.SysUserRoleService;
import com.yunshuju.service.SysUserService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YuYanHui
 * @since 2018-09-06
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDao, SysUser> implements SysUserService {

	@Resource
	private SysUserRoleService sysUserRoleService;
	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private IdGenerateFactory idGenerateFactory;


	@Override
	public SysUser findByUsername(String username) {
		return selectOne(new EntityWrapper<SysUser>().eq("username", username));
	}

	@Override
	public SysUser findByUsernameAndPassword(String username, String password) {
		String encoderPassword = MD5Util.encode(password);
		return selectOne(new EntityWrapper<SysUser>().eq("username", username).eq("password", encoderPassword));
	}

	@Override
	public SysRole findRoleById(String userId) {
		SysUserRole sysUserRole = sysUserRoleService.findRoleByUserId(userId);
		return sysRoleService.selectById(sysUserRole.getRoleId());
	}

	@Override
	public List<SysRole> findRolesById(String userId) {
		List<SysUserRole> sysUserRoles = sysUserRoleService.findRolesByUserId(userId);
		if (CollectionUtils.isEmpty(sysUserRoles)) {
			return new ArrayList<>();
		}
		//对象列表转换为字符串列表
		List<String> stringList = sysUserRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
		return sysRoleService.selectBatchIds(stringList);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public SysUser add(SysUser sysUser) {
		SysUser user = findByUsername(sysUser.getUsername());
		if (user != null) {
			throw new UsernameAlreadyExistException();
		}
		String userId = idGenerateFactory.nextStringId();
		sysUser.setPassword(MD5Util.encode(sysUser.getPassword()));
		sysUser.setId(userId);
		insert(sysUser);
		//关联查询获取最新的用户信息
//		SysUser newSysUser = sysUserDao.findUserById(userId);
		return sysUser;
	}

}
