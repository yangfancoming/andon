package com.sim.andon.web.entity;

import com.sim.andon.web.core.jdbc.annotation.Key;
import com.sim.andon.web.core.jdbc.annotation.Table;

@Table(name = "T_ROLE")
public class Role {
	@Key
	private String id;
	private String code;  //角色编码
	private String name;  //角色名称
	private String remark;   //描述信息
	private String workshopId;
	private String isManager;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getWorkshopId() {
		return workshopId;
	}

	public void setWorkshopId(String workshopId) {
		this.workshopId = workshopId;
	}

	public String getIsManager() {
		return isManager;
	}

	public void setIsManager(String isManager) {
		this.isManager = isManager;
	}
}