package com.yunshuju.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.yunshuju.domain.entity.SysRole;
import com.yunshuju.domain.entity.SysUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YuYanHui
 * @since 2018-09-06
 */
public interface SysUserService extends IService<SysUser> {

	SysUser findByUsername(String username);

	SysUser findByUsernameAndPassword(String username, String password);

	SysRole findRoleById(String userId);

	List<SysRole> findRolesById(String userId);

	SysUser add(SysUser sysUser);

}
