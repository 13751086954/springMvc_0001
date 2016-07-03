package com.permission.model.vo;

import java.util.ArrayList;
import java.util.List;

import com.permission.pojo.ModuleElementWithBLOBs;

/**
 * 
 * @author milanyangbo
 *
 */
public class ModuleView {

	public  ModuleView() {
		this.childern= new ArrayList<ModuleView>();
		this.elements= new ArrayList<ModuleElementWithBLOBs>();
	}

	/**
	 * ID
	 */
	private int id;

	/**
	 * 组织名称
	 */
	private String name;


	/**
	 * 主页面URL
	 */
	private String url;


	/**
	 * 父节点流水号
	 */
	private int parentid;


	/**
	 * 节点图标文件名称
	 */
	private String iconname;

	private String cascadeid;



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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public String getIconname() {
		return iconname;
	}

	public void setIconname(String iconname) {
		this.iconname = iconname == null ? null : iconname.trim();
	}

	/**
	 * 子节点
	 */
	 private List<ModuleView> childern ;

	public List<ModuleView> getChildern() {
		return childern;
	}

	public void setChildern(List<ModuleView> childern) {
		this.childern = childern;
	}


	/**
	 * 模块中的元素
	 */
	private List<ModuleElementWithBLOBs> elements;


	public List<ModuleElementWithBLOBs> getElements() {
		return elements;
	}

	public void setElements(List<ModuleElementWithBLOBs> elements) {
		this.elements = elements;
	}

	public String getCascadeid(String cascadeid) {
		// TODO Auto-generated method stub
		return this.cascadeid;
	}

	public void setCascadeid(String cascadeid) {
		// TODO Auto-generated method stub
		this.cascadeid=cascadeid;
	}

}
