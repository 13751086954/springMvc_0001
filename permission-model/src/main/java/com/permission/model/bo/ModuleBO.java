package com.permission.model.bo;

import java.util.List;

import com.permission.pojo.Module;


public class ModuleBO {
   
	private Integer total;
	
	private List<Module> list;
	
	private Integer pageCurrent;
	
	public  Integer getTotal() {
	    return total;
	}

	public void setTotal(Integer total) {	    	
	    this.total = total;
    }
	
	public  List<Module> getList() {
	    return list;
	}

	public void setList(List<Module> list) {	    	
	    this.list = list;
    }
	
	public  Integer getPageCurrent() {
	    return pageCurrent;
	}

	public void setPageCurrent(Integer pageCurrent) {	    	
	    this.pageCurrent = pageCurrent;
    }
}
