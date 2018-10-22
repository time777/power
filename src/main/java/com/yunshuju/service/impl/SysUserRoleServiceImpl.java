package com.yunshuju.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yunshuju.domain.dao.SysUserRoleDao;
import com.yunshuju.domain.entity.SysUserRole;
import com.yunshuju.service.BaseServiceImpl;
import com.yunshuju.service.SysUserRoleService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YuYanHui
 * @since 2018-09-06
 */
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRoleDao, SysUserRole> implements SysUserRoleService {

	@Override
	public List<SysUserRole> findRolesByUserId(String userId) {
		return selectList(new EntityWrapper<SysUserRole>().eq("user_id", userId));
	}

	@Override
	public SysUserRole findRoleByUserId(String userId) {
		return selectOne(new EntityWrapper<SysUserRole>().eq("user_id", userId));
	}

}
