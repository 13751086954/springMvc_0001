package com.permission.model.bo;

import java.util.List;

import com.permission.pojo.Module;


public class ModuleBO {
   
	private int total;
	
	private List<Module> list;
	
	private int pageCurrent;
	
	public  int getTotal() {
	    return total;
	}

	public void setTotal(int total) {	    	
	    this.total = total;
    }
	
	public  List<Module> getList() {
	    return list;
	}

	public void setList(List<Module> list) {	    	
	    this.list = list;
    }
	
	public  int getPageCurrent() {
	    return pageCurrent;
	}

	public void setPageCurrent(int pageCurrent) {	    	
	    this.pageCurrent = pageCurrent;
    }
}
