package com.permission.pojo;

/**
 * 分类表
 * @author milanyangbo
 *
 */
public class Category {

	public Category(){
		this.id= 0;
		this.cascadeid= "";
		this.name= "";
		this.parentid= 0;
		this.status= 0;
		this.sortno= 0;
		this.rootkey= "";
		this.rootname= "";
	}
	/**
	 * 分类表ID
	 */
	private int id;

	/**
	 * 节点语义ID
	 */
	private String cascadeid;

	/**
	 * 节点语义ID
	 */
	private String name;

	/**
	 * 节点语义ID
	 */
	private int parentid;

	/**
	 * 当前状态
	 */
	private int status;

	/**
	 * 排序号
	 */
	private int sortno;

	/**
	 * 分类所属科目
	 */
	private String rootkey;

	/**
	 * 分类所属科目名称
	 */
	private String rootname;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCascadeid() {
		return cascadeid;
	}

	public void setCascadeid(String cascadeid) {
		this.cascadeid = cascadeid == null ? null : cascadeid.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSortno() {
		return sortno;
	}

	public void setSortno(int sortno) {
		this.sortno = sortno;
	}

	public String getRootkey() {
		return rootkey;
	}

	public void setRootkey(String rootkey) {
		this.rootkey = rootkey == null ? null : rootkey.trim();
	}

	public String getRootname() {
		return rootname;
	}

	public void setRootname(String rootname) {
		this.rootname = rootname == null ? null : rootname.trim();
	}

}