package com.yunshuju.service;

import java.util.List;
import java.util.Set;

import com.yunshuju.domain.entity.SysResource;
import com.yunshuju.domain.entity.SysRole;
import com.yunshuju.domain.entity.SysUser;

/**
 * 功能：
 *
 * @author： YuYanHui(yuyh@dtdream.com)
 * @create： 2018-09-18 15:30:58
 * @version： 2018 Version 1.0
 * @company： 数梦工场 Created with IntelliJ IDEA
 */
public interface SecurityService {

	SysUser findUserByUsername(String username);

	SysUser findUserByUsernameAndPassword(String username, String password);

	Set<SysRole> findRolesByUserId(String userId);

	List<SysResource> findAllPermResource();

	Set<String> findAllRoleName();

	List<SysRole> findAllRole();

//	String findRoleIdByUserId(String userId);

	List<SysResource> findPermResourceByRoleId(String roleId);

	SysResource findResourceById(String id);

	SysUser login(String userName, String password, boolean rememberMe);

}
