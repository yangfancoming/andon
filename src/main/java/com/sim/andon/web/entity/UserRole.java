package com.sim.andon.web.entity;

import com.sim.andon.web.core.jdbc.annotation.Table;

@Table(name="t_user_role")
public class UserRole {
	private String userCode;
    //角色编码
    private String roleId;
	private String workshopId;
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getWorkshopId() {
		return workshopId;
	}

	public void setWorkshopId(String workshopId) {
		this.workshopId = workshopId;
	}
}
