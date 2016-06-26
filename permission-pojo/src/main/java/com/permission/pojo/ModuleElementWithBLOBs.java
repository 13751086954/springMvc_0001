package com.permission.pojo;

public class ModuleElementWithBLOBs extends ModuleElement {

	public ModuleElementWithBLOBs(){
		this.id= 0;
		this.domid= "";
		this.name= "";
		this.type= "";
		this.moduleid= 0;
		this.attr= "";
		this.script= "";
		this.icon= "";
		this.classify= "";
		this.remark= "";
		this.sort= 0;
	}
	/**
	 * 元素附加属性
	 */
	private String attr;

	/**
	 * 元素调用脚本
	 */
	private String script;

	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr == null ? null : attr.trim();
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script == null ? null : script.trim();
	}


}