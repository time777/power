package com.yunshuju.domain.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;

/**
 * <p>
 * 
 * </p>
 *
 * @author YuYanHui
 * @since 2018-09-06
 */
@TableName("sys_role_resource")
public class SysRoleResource extends Model<SysRoleResource> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    @TableId("role_id")
	private String roleId;
    /**
     * 资源id
     */
	@TableField("resource_id")
	private String resourceId;
	@TableField(fill = FieldFill.INSERT)
	private Date created;


	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	protected Serializable pkVal() {
		return this.roleId;
	}

	@Override
	public String toString() {
		return "SysRoleResource{" +
			"roleId=" + roleId +
			", resourceId=" + resourceId +
			", created=" + created +
			"}";
	}
}
