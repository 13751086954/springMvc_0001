package com.permission.model.bo;

import java.util.List;

import com.permission.pojo.Role;

public class RoleBO {

   private List<Role> list;
	
	private Integer pageCurrent;

	private Integer total;
	
	public  Integer getTotal() {
	    return total;
	}

	public void setTotal(Integer total) {	    	
	    this.total = total;
    }
	
	public  List<Role> getList() {
	    return list;
	}

	public void setList(List<Role> list) {	    	
	    this.list = list;
    }
	
	public  Integer getPageCurrent() {
	    return pageCurrent;
	}

	public void setPageCurrent(Integer pageCurrent) {	    	
	    this.pageCurrent = pageCurrent;
    }
}
