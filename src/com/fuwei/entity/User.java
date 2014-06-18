package com.fuwei.entity;

import java.io.Serializable;
import java.util.Date;

import net.keepsoft.commons.annotation.IdentityId;
import net.keepsoft.commons.annotation.Table;

@Table("tb_user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1173628226284035541L;
	@IdentityId
	private int id;
	private String username ; //用户名（登录时使用）
	private String name;//姓名,一般是中文名称
	private String help_code;//拼音简称
	
	private Integer roleId;//角色（决定有哪些权限）

	private Boolean inUse;//是否启用

	private Date created_at;//创建时间

	private Date updated_at;//最近更新时间

	private String tel;//手机

	private String email;//邮箱

	private String qq;//QQ


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public Boolean getInUse() {
		return inUse;
	}

	public void setInUse(Boolean inUse) {
		this.inUse = inUse;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHelp_code() {
		return help_code;
	}

	public void setHelp_code(String help_code) {
		this.help_code = help_code;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}


}
