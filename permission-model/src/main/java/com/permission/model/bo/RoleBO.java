package com.permission.model.bo;

import java.util.List;

import com.permission.pojo.Role;

public class RoleBO {

   private List<Role> list;
	
	private int pageCurrent;

	private int total;
	
	public  int getTotal() {
	    return total;
	}

	public void setTotal(int total) {	    	
	    this.total = total;
    }
	
	public  List<Role> getList() {
	    return list;
	}

	public void setList(List<Role> list) {	    	
	    this.list = list;
    }
	
	public  int getPageCurrent() {
	    return pageCurrent;
	}

	public void setPageCurrent(int pageCurrent) {	    	
	    this.pageCurrent = pageCurrent;
    }
}
