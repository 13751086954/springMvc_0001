package com.permission.pojo;

/**
 * 键值参数
 * @author milanyangbo
 *
 */
public class Param {

	public Param(){
		this.id= 0;
		this.value= "";
		this.key= "";
		this.categoryid= 0;
		this.sortno= 0;
		this.status= 0;
		this.description= "";
	}
	/**
	 * ID
	 */
	private int id;

	/**
	 * 值
	 */
	private String value;


	/**
	 * 
	 */
	private String key;

	/**
	 * 所属分类
	 */
	private int categoryid;

	/**
	 * 排序号
	 */
	private int sortno;

	/**
	 * 状态
	 */
	private int status;

	/**
	 * 描述
	 */
	private String description;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value == null ? null : value.trim();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key == null ? null : key.trim();
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public int getSortno() {
		return sortno;
	}

	public void setSortno(int sortno) {
		this.sortno = sortno;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

}