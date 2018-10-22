package com.yunshuju.domain;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * 功能：用户模型 实现默认的User对象，把自定义的用户信息存入
 * 		同时也保存成Session里面的对象信息
 *
 * @author： YuYanHui(yuyh@dtdream.com)
 * @create： 2018-09-21 15:51:34
 * @version： 2018 Version 1.0
 * @company： 数梦工场 Created with IntelliJ IDEA
 */
public class UserModel extends User {

	private String id;
	private String nickName;
	private Date created;


	public UserModel(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
