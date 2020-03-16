package com.sim.andon.web.entity;

import com.sim.andon.web.core.jdbc.annotation.Key;
import com.sim.andon.web.core.jdbc.annotation.NotRecord;
import com.sim.andon.web.core.jdbc.annotation.Table;

@Table(name = "t_persons")
public class Persons {
	@Key
	private String id;

	private String personCode;// 工号
	private String personImg;// 照片
	private String duty;// 责任
	private String tel;// 移动电话
	private String shortTel;//座机电话
	private String mail;// 邮件
	private String username;// 用户名
	private String password;// 密码
	private String name;// 姓名
	private String positionId;// 职位
	private String workshopId;// 所属部门
	private String lineId;// 所属产线
	private String dept;// 部门
	private String isAuditPerson;// 是否是分层审核负责人，0：不是分层审核负责人，1：是分层审核负责人
	private String is5sAuditPerson;// 是否是5S审核负责人，0：不是5S审核负责人，1：是5S审核负责人

	private String technologicalProcessId;// 工艺流程

	private String technological;// 技术？工艺？

	private String cardNo;// 员工卡id号
	private String isTpm = "0";// 是否为TPM 0：不是 1：是
	private String notificationTypeId;// 异常升级通知类型 可多选
	private String isLpa = "0"; //0：不是 1：是
    
	private String roleCode;  //权限code
	@NotRecord
	private String positionName;// 职位名称

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public String getPersonImg() {
		return personImg;
	}

	public void setPersonImg(String personImg) {
		this.personImg = personImg;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getWorkshopId() {
		return workshopId;
	}

	public void setWorkshopId(String workshopId) {
		this.workshopId = workshopId;
	}

	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getTechnologicalProcessId() {
		return technologicalProcessId;
	}

	public void setTechnologicalProcessId(String technologicalProcessId) {
		this.technologicalProcessId = technologicalProcessId;
	}

	public String getIsAuditPerson() {
		return isAuditPerson;
	}

	public void setIsAuditPerson(String isAuditPerson) {
		this.isAuditPerson = isAuditPerson;
	}

	public String getIs5sAuditPerson() {
		return is5sAuditPerson;
	}

	public void setIs5sAuditPerson(String is5sAuditPerson) {
		this.is5sAuditPerson = is5sAuditPerson;
	}

	public String getTechnological() {
		return technological;
	}

	public void setTechnological(String technological) {
		this.technological = technological;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getIsTpm() {
		return isTpm;
	}

	public void setIsTpm(String isTpm) {
		this.isTpm = isTpm;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getNotificationTypeId() {
		return notificationTypeId;
	}

	public void setNotificationTypeId(String notificationTypeId) {
		this.notificationTypeId = notificationTypeId;
	}

	public String getIsLpa() {
		return isLpa;
	}

	public void setIsLpa(String isLpa) {
		this.isLpa = isLpa;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getShortTel() {
		return shortTel;
	}

	public void setShortTel(String shortTel) {
		this.shortTel = shortTel;
	}
}
