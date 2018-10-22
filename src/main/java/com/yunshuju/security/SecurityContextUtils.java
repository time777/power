package com.yunshuju.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;

import com.yunshuju.domain.UserModel;

/**
 * 功能：对用户、id、资源对象等的操作
 *
 * @author： YuYanHui(yuyh@dtdream.com)
 * @create： 2018-09-17 16:16:44
 * @version： 2018 Version 1.0
 * @company： 数梦工场 Created with IntelliJ IDEA
 */
public abstract class SecurityContextUtils {

	public static final String USER_SESSION_KEY = "user";


	/**
	 * 获取登录成功的用户对象ID
	 *
	 * @return
	 */
	public static String getCurrentUserId() {
		UserModel userModel = getCurrentUser();
		return userModel == null ? null : userModel.getId();
	}

	/**
	 * 获取登录成功的用户对象信息
	 *
	 * @return
	 */
	public static UserModel getCurrentUser() {
		Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!object.equals("anonymousUser")) {
			return (UserModel) object;
		}
		return null;
	}


	/**
	 * 得到Session
	 *
	 * @param request
	 * @return
	 */
	public static HttpSession getSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return session;
	}

}
