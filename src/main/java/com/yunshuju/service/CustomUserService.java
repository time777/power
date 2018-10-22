package com.yunshuju.service;

import lombok.extern.slf4j.Slf4j;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yunshuju.domain.UserModel;
import com.yunshuju.domain.entity.SysRole;
import com.yunshuju.domain.entity.SysUser;
import com.yunshuju.security.SecurityContextUtils;

/**
 * 功能：身份认证管理器
 * 		重写方法，自定义进行身份认证登录
 *
 * @author： YuYanHui(yuyh@dtdream.com)
 * @create： 2018-09-20 10:21:17
 * @version： 2018 Version 1.0
 * @company： 数梦工场 Created with IntelliJ IDEA
 */
@Slf4j
@Service
public class CustomUserService implements UserDetailsService {

	@Autowired
	private SecurityService securityService;

	@Autowired
	private HttpSession session;


	/**
	 * 通过用户名加载用户信息，重写该方法用于记住密码后通过Cookie登录
	 * 校验用户信息并将用户信息放在Session中
	 *
	 * @param username
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser sysUser = securityService.findUserByUsername(username);
		if (sysUser == null) {
			throw new UsernameNotFoundException("用户名不存在");
		}
		else if (!sysUser.isEnabled()) {
			throw new AccountExpiredException("帐户已被禁用 ");
		} else {
			Set<SysRole> sysRoleSet = securityService.findRolesByUserId(sysUser.getId());
			sysUser.setSysRoleSet(sysRoleSet);
			UserModel userModel = new UserModel(username, sysUser.getPassword(), sysUser.getAuthorities());
			userModel.setId(sysUser.getId());
			userModel.setNickName(sysUser.getNickName());
			userModel.setCreated(sysUser.getCreated());
			//当用户信息有效时放入Session中
			session.setAttribute(SecurityContextUtils.USER_SESSION_KEY, userModel);
			return userModel;
		}
	}


}
