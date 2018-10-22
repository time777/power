package com.yunshuju.domain.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
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
@TableName("sys_resource")
public class SysResource extends Model<SysResource> {

    private static final long serialVersionUID = 1L;

	private String id;
    /**
     * 父级id，父级为0
     */
	private String pid;
    /**
     * 名称
     */
	private String name;
    /**
     * 类型 0目录、1菜单、2按钮、3API
     */
	private Integer type;
    /**
     * 请求方式 GET POST PUT DELETE
     */
	private String method;
	/**
	 *
	 */
	@TableField("data_permission")
	private String dataPermission;
    /**
     * 请求地址
     */
	private String api;
    /**
     * 备注
     */
	private String remarks;
	/**
	 * 排序，升序从1开始
	 */
	private Integer sort;
	@TableField(fill = FieldFill.INSERT)
	private Date created;
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updated;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getDataPermission() {
		return dataPermission;
	}

	public void setDataPermission(String dataPermission) {
		this.dataPermission = dataPermission;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SysResource{" +
			"id=" + id +
			", pid=" + pid +
			", name=" + name +
			", type=" + type +
			", method=" + method +
			", api=" + api +
			", remarks=" + remarks +
			", created=" + created +
			", updated=" + updated +
			"}";
	}
}
