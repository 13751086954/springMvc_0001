package com.permission.pojo;

/**
 * 用户扩展信息表
 * @author milanyangbo
 *
 */
public class UserExt {

	public UserExt(){
		this.id= 0;
		this.email= "";
		this.phone= "";
		this.mobile= "";
		this.address= "";
		this.zip= "";
		this.birthday= "";
		this.idcard= "";
		this.qq= "";
		this.bytearrayid= 0;
		this.field1= "";
		this.field2= "";
		this.field3= "";
	}
	/**
	 * 用户ID
	 */
	protected int id;

	/**
	 * 电子邮件
	 */
	protected String email;

	/**
	 * 固定电话
	 */
	protected String phone;

	/**
	 * 移动电话
	 */
	protected String mobile;

	/**
	 * 联系地址
	 */
	protected String address;

	/**
	 * 邮编
	 */
	protected String zip;

	/**
	 * 生日
	 */
	protected String birthday;

	/**
	 *  身份证号
	 */
	protected String idcard;

	/**
	 * QQ
	 */
	protected String qq;

	/**
	 * 用户头像流文件ID
	 */
	protected int bytearrayid;

	/**
	 * 静态扩展字段1
	 */
	protected String field1;

	/**
	 * 静态扩展字段2
	 */
	protected String field2;

	/**
	 *  静态扩展字段3
	 */
	protected String field3;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip == null ? null : zip.trim();
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday == null ? null : birthday.trim();
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard == null ? null : idcard.trim();
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq == null ? null : qq.trim();
	}

	public int getBytearrayid() {
		return bytearrayid;
	}

	public void setBytearrayid(int bytearrayid) {
		this.bytearrayid = bytearrayid;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1 == null ? null : field1.trim();
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2 == null ? null : field2.trim();
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3 == null ? null : field3.trim();
	}
}