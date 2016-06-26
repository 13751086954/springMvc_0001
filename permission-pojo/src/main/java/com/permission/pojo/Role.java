package com.permission.pojo;

import java.util.Date;

/**
 * 角色表
 * @author milanyangbo
 *
 */
public class Role {

	public Role(){
		this.id= 0;
		this.name= "";
		this.status= 0;
		this.type= 0;
		this.createtime= new Date(System.currentTimeMillis());
		this.createid= "";
		this.orgid= 0;
		this.orgcascadeid= "";
		this.orgname= "";
	}

	/**
	 *  流水号
	 */
	private int id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 状态
	 */
	private int status;

	/**
	 * 角色类型
	 */
	private int type;

	/**
	 * 创建时间
	 */
	private Date createtime;

	/**
	 * 创建人ID
	 */
	private String createid;

	/**
	 * 所属部门流水号
	 */
	private int orgid;

	/**
	 * 所属部门节点语义ID
	 */
	private String orgcascadeid;

	/**
	 * 所属部门名称
	 */
	private String orgname;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getCreateid() {
		return createid;
	}

	public void setCreateid(String createid) {
		this.createid = createid == null ? null : createid.trim();
	}

	public int getOrgid() {
		return orgid;
	}

	public void setOrgid(int orgid) {
		this.orgid = orgid;
	}

	public String getOrgcascadeid() {
		return orgcascadeid;
	}

	public void setOrgcascadeid(String orgcascadeid) {
		this.orgcascadeid = orgcascadeid == null ? null : orgcascadeid.trim();
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname == null ? null : orgname.trim();
	}

}