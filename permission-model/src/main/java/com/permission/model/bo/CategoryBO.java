package com.permission.model.bo;

import java.util.List;

import com.permission.pojo.Category;

public class CategoryBO {

private Integer total;
	
	private List<Category> list;
	
	private Integer pageCurrent;
	
	public  Integer getTotal() {
	    return total;
	}

	public void setTotal(Integer total) {	    	
	    this.total = total;
    }
	
	public  List<Category> getList() {
	    return list;
	}

	public void setList(List<Category> list) {	    	
	    this.list = list;
    }
	
	public  Integer getPageCurrent() {
	    return pageCurrent;
	}

	public void setPageCurrent(Integer pageCurrent) {	    	
	    this.pageCurrent = pageCurrent;
    }
}
