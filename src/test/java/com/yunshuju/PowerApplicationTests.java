package com.yunshuju;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yunshuju.config.properties.AppProperties;
import com.yunshuju.domain.entity.SysRoleResource;
import com.yunshuju.domain.entity.SysUser;
import com.yunshuju.service.SysRoleResourceService;
import com.yunshuju.service.SysUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PowerApplicationTests {

	@Resource
	private SysUserService sysUserService;
	@Resource
	private AppProperties appProperties;
	@Resource
	private SysRoleResourceService sysRoleResourceService;

	@Test
	public void contextLoads() {
		SysUser sysUser = new SysUser();
		sysUser.setUsername("admin");
		sysUser.setNickName("管理员");
		sysUser.setPassword(appProperties.getUserDefaultPassword());
		sysUserService.add(sysUser);
	}

	@Test
	public void addRoleResource() {
		SysRoleResource sysRoleResource = new SysRoleResource();
		sysRoleResource.setRoleId("3");
		sysRoleResource.setResourceId("3");
		sysRoleResource.setCreated(new Date());
		sysRoleResourceService.add(sysRoleResource);
	}

}
