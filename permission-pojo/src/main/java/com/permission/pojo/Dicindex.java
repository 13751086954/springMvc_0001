package com.permission.pojo;

/**
 *  数据字典
 * @author milanyangbo
 *
 */
public class Dicindex {

	public Dicindex(){
		this.id= 0;
		this.name= "";
		this.key= "";
		this.sortno= 0;
		this.categoryid= 0;
		this.description= "";
	}
	/**
	 * 数据字典ID
	 */
	private int id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 
	 */
	private String key;

	/**
	 * 排序号
	 */
	private int sortno;

	/**
	 * 所属分类
	 */
	private int categoryid;


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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key == null ? null : key.trim();
	}

	public int getSortno() {
		return sortno;
	}

	public void setSortno(int sortno) {
		this.sortno = sortno;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}
}