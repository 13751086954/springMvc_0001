package com.permission.model.bo;

import java.util.List;

import com.permission.model.vo.UserView;

public class UserBO {
	   
	private int total;
	
	private List<UserView> list;
	
	private int pageCurrent;
	
	public  int getTotal() {
	    return total;
	}

	public void setTotal(int total) {	    	
	    this.total = total;
    }
	
	public  List<UserView> getList() {
	    return list;
	}

	public void setList(List<UserView> list) {	    	
	    this.list = list;
    }
	
	public  int getPageCurrent() {
	    return pageCurrent;
	}

	public void setPageCurrent(int pageCurrent) {	    	
	    this.pageCurrent = pageCurrent;
    }
}
