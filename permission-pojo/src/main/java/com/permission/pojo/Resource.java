package com.permission.pojo;

/**
 * 资源表
 * @author milanyangbo
 *
 */
public class Resource {
	/**
	 * 资源表ID
	 */
    private int id;

    /**
	 * 节点语义ID
	 */
    private String cascadeid;

    /**
   	 * 
   	 */
    private String key;
    
    /**
   	 *父节点流水号
   	 */
    private int parentid;


    /**
   	 * 名称
   	 */
    private String name;

    /**
   	 * 排序号
   	 */
    private int sortno;

    /**
   	 * 状态
   	 */
    private int status;
    
    /**
   	 * 资源分类
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

    public String getCascadeid() {
        return cascadeid;
    }

    public void setCascadeid(String cascadeid) {
        this.cascadeid = cascadeid == null ? null : cascadeid.trim();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
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