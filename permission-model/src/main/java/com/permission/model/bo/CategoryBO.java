package com.permission.model.bo;

import java.util.List;

import com.permission.pojo.Category;

public class CategoryBO {

    private int total;
	
	private List<Category> list;
	
	private int pageCurrent;
	
	public  int getTotal() {
	    return total;
	}

	public void setTotal(int total) {	    	
	    this.total = total;
    }
	
	public  List<Category> getList() {
	    return list;
	}

	public void setList(List<Category> list) {	    	
	    this.list = list;
    }
	
	public  int getPageCurrent() {
	    return pageCurrent;
	}

	public void setPageCurrent(int pageCurrent) {	    	
	    this.pageCurrent = pageCurrent;
    }
}
