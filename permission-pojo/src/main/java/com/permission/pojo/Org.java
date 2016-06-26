package com.permission.pojo;

import java.util.Date;

/**
 * 组织表
 * @author milanyangbo
 *
 */
public class Org {

	public Org() {
		this.id= 0;
		this.cascadeid= "";
		this.name= "";
		this.hotkey= "";
		this.parentid= 0;
		this.parentname= "";
		this.iconname= "";
		this.status= 0;
		this.type= 0;
		this.bizcode= "";
		this.customcode= "";
		this.createtime= new Date(System.currentTimeMillis());
		this.createid= 0;
		this.sortno= 0;
	}
	/**
	 * 流水号
	 */
	private int id;

	/**
	 * 节点语义ID
	 */
	private String cascadeid;

	/**
	 * 组织名称
	 */
	private String name;

	/**
	 * 热键
	 */
	private String hotkey;

	/**
	 * 父节点流水号
	 */
	private int parentid;

	/**
	 * 父节点名称
	 */
	private String parentname;

	/**
	 * 是否叶子节点
	 */
	private Boolean isleaf;

	/**
	 * 是否自动展开
	 */
	private Boolean isautoexpand;

	/**
	 *  节点图标文件名称
	 */
	private String iconname;

	/**
	 *当前状态
	 */
	private int status;

	/**
	 * 组织类型
	 */
	private int type;

	/**
	 * 业务对照码
	 */
	private String bizcode;

	/**
	 * 创建时间
	 */
	private Date createtime;

	/**
	 * 创建人ID
	 */
	private int createid;

	/**
	 * 排序号
	 */
	private int sortno;

	/**
	 * 自定义扩展码
	 */
	private String customcode;


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

	public String getHotkey() {
		return hotkey;
	}

	public void setHotkey(String hotkey) {
		this.hotkey = hotkey == null ? null : hotkey.trim();
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public String getParentname() {
		return parentname;
	}

	public void setParentname(String parentname) {
		this.parentname = parentname == null ? null : parentname.trim();
	}

	public Boolean getIsleaf() {
		return isleaf;
	}

	public void setIsleaf(Boolean isleaf) {
		this.isleaf = isleaf;
	}

	public Boolean getIsautoexpand() {
		return isautoexpand;
	}

	public void setIsautoexpand(Boolean isautoexpand) {
		this.isautoexpand = isautoexpand;
	}

	public String getIconname() {
		return iconname;
	}

	public void setIconname(String iconname) {
		this.iconname = iconname == null ? null : iconname.trim();
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

	public String getBizcode() {
		return bizcode;
	}

	public void setBizcode(String bizcode) {
		this.bizcode = bizcode == null ? null : bizcode.trim();
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public int getCreateid() {
		return createid;
	}

	public void setCreateid(int createid) {
		this.createid = createid;
	}

	public int getSortno() {
		return sortno;
	}

	public void setSortno(int sortno) {
		this.sortno = sortno;
	}

	public String getCustomcode() {
		return customcode;
	}

	public void setCustomcode(String customcode) {
		this.customcode = customcode == null ? null : customcode.trim();
	}

}