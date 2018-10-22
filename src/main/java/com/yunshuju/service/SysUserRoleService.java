package com.yunshuju.service;

import java.util.List;

import com.yunshuju.domain.entity.SysUserRole;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YuYanHui
 * @since 2018-09-06
 */
public interface SysUserRoleService extends IService<SysUserRole> {

	List<SysUserRole> findRolesByUserId(String userId);

	SysUserRole findRoleByUserId(String userId);
}
