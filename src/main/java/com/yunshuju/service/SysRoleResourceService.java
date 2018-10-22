package com.yunshuju.service;

import com.baomidou.mybatisplus.service.IService;
import com.yunshuju.domain.entity.SysRoleResource;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YuYanHui
 * @since 2018-09-06
 */
public interface SysRoleResourceService extends IService<SysRoleResource> {

	boolean add(SysRoleResource sysRoleResource);

	boolean delete(String id);

}
