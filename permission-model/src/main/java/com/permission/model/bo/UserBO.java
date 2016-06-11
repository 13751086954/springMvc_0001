package com.permission.model.bo;

import java.util.List;

import com.permission.model.vo.UserView;

public class UserBO {
	   
	private Integer total;
	
	private List<UserView> list;
	
	private Integer pageCurrent;
	
	public  Integer getTotal() {
	    return total;
	}

	public void setTotal(Integer total) {	    	
	    this.total = total;
    }
	
	public  List<UserView> getList() {
	    return list;
	}

	public void setList(List<UserView> list) {	    	
	    this.list = list;
    }
	
	public  Integer getPageCurrent() {
	    return pageCurrent;
	}

	public void setPageCurrent(Integer pageCurrent) {	    	
	    this.pageCurrent = pageCurrent;
    }
}
