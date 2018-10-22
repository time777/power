package com.yunshuju.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;

/**
 * <p>
 * 实现UserDetails的方法，认证、权限信息
 * </p>
 *
 * @author YuYanHui
 * @since 2018-09-06
 */
@TableName("sys_user")
public class SysUser extends Model<SysUser> implements UserDetails {

    private static final long serialVersionUID = 1L;

	private String id;
    /**
     * 用户名
     */
	private String username;
    /**
     * 密码
     */
	private String password;
    /**
     * 昵称
     */
	@TableField("nick_name")
	private String nickName;
	@TableField(fill = FieldFill.INSERT)
	private String creator;
	@TableField(fill = FieldFill.INSERT)
	private Date created;
	/**
	 * 是否激活/可用 默认为1 true 0 false
	 */
	@TableField("is_enabled")
	private boolean isEnabled;

	/**
	 * 用户的角色，数据库不维护
	 */
	@TableField(exist = false)
	private Set<SysRole> sysRoleSet = new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		for (SysRole sysRole : sysRoleSet) {
			SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(sysRole.getName());
			grantedAuthorities.add(simpleGrantedAuthority);
		}
		return grantedAuthorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean enabled) {
		isEnabled = enabled;
	}

	public Set<SysRole> getSysRoleSet() {
		return sysRoleSet;
	}

	public void setSysRoleSet(Set<SysRole> sysRoleSet) {
		this.sysRoleSet = sysRoleSet;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SysUser{" +
				"id=" + id +
				", username=" + username +
				", password=" + password +
				", nickName=" + nickName +
				", creator=" + creator +
				", created=" + created +
				", isEnabled=" + isEnabled +
				"}";
	}
}
