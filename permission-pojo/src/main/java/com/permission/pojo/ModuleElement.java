package com.permission.pojo;

/**
 * 模块元素表(需要权限控制的按钮)
 * @author milanyangbo
 *
 */
public class ModuleElement {
	/**
	 * 流水号
	 */
    private int id;
    
    /**
	 * DOM ID
	 */
    private String domid;

    /**
	 * 名称
	 */
    private String name;

    /**
	 * 类型
	 */
    private String type;

    /**
	 * 功能模块Id
	 */
    private int moduleid;

    /**
	 * 元素图标
	 */
    private String icon;

    /**
   	 * 元素样式
   	 */
    private String classify;

    /**
   	 * 备注
   	 */
    private String remark;

    /**
   	 * 排序字段
   	 */
    private int sort;
     

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDomid() {
        return domid;
    }

    public void setDomid(String domid) {
        this.domid = domid == null ? null : domid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public int getModuleid() {
        return moduleid;
    }

    public void setModuleid(int moduleid) {
        this.moduleid = moduleid;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify == null ? null : classify.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}