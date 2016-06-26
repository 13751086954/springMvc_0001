package com.permission.pojo;

public class UserExtWithBLOBs extends UserExt {

	public UserExtWithBLOBs(){
		this.id= 0;
		this.email= "";
		this.phone= "";
		this.mobile= "";
		this.address= "";
		this.zip= "";
		this.birthday= "";
		this.idcard= "";
		this.qq= "";
		this.dynamicfield= "";
		this.bytearrayid= 0;
		this.remark= "";
		this.field1= "";
		this.field2= "";
		this.field3= "";
	}

	/**
	 * 动态扩展字段
	 */
	private String dynamicfield;

	/**
	 * 备注
	 */
	private String remark;

	public String getDynamicfield() {
		return dynamicfield;
	}

	public void setDynamicfield(String dynamicfield) {
		this.dynamicfield = dynamicfield == null ? null : dynamicfield.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}
}