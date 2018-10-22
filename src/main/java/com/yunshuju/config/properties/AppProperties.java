package com.yunshuju.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 功能：系统默然配置
 *
 * @author： YuYanHui(yuyh@dtdream.com)
 * @create： 2018-09-07 11:24:06
 * @version： 2018 Version 1.0
 * @company： 数梦工场 Created with IntelliJ IDEA
 */
@ConfigurationProperties("app")
@Component
public class AppProperties {

	private String adminId;
	private String loginApi;
	private String permCachePrefix;
	private long permCacheExpire;
	/**
	 * 用户默认密码
	 */
	private String userDefaultPassword;


	public String getUserDefaultPassword() {
		return userDefaultPassword;
	}

	public void setUserDefaultPassword(String userDefaultPassword) {
		this.userDefaultPassword = userDefaultPassword;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getLoginApi() {
		return loginApi;
	}

	public void setLoginApi(String loginApi) {
		this.loginApi = loginApi;
	}

	public String getPermCachePrefix() {
		return permCachePrefix;
	}

	public void setPermCachePrefix(String permCachePrefix) {
		this.permCachePrefix = permCachePrefix;
	}

	public long getPermCacheExpire() {
		return permCacheExpire;
	}

	public void setPermCacheExpire(long permCacheExpire) {
		this.permCacheExpire = permCacheExpire;
	}
}
