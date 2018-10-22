package com.yunshuju.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yunshuju.common.Result;
import com.yunshuju.domain.entity.SysRoleResource;
import com.yunshuju.service.SysRoleResourceService;

/**
 * 功能：
 *
 * @author： YuYanHui(yuyh@dtdream.com)
 * @create： 2018-10-15 15:05:05
 * @version： 2018 Version 1.0
 * @company： 数梦工场 Created with IntelliJ IDEA
 */
@RestController
@RequestMapping("/api")
public class SysController {

	@Resource
	private SysRoleResourceService sysRoleResourceService;

//	@GetMapping("/users")
//	public String findUsers() {
//		return "/users";
//	}


//	@GetMapping("/sys")
//	public String findSys() {
//		return "/sys";
//	}


	@PostMapping("/roleResources")
	public Result addRoleResource(@RequestBody SysRoleResource sysRoleResource) {
		sysRoleResourceService.add(sysRoleResource);
		return Result.success(sysRoleResource);
	}

}
