package com.permission.model.bo;

import java.util.List;

import com.permission.pojo.Resource;

public class ResourceBO {

private Integer total;
	
	private List<Resource> list;
	
	private Integer pageCurrent;
	
	public  Integer getTotal() {
	    return total;
	}

	public void setTotal(Integer total) {	    	
	    this.total = total;
    }
	
	public  List<Resource> getList() {
	    return list;
	}

	public void setList(List<Resource> list) {	    	
	    this.list = list;
    }
	
	public  Integer getPageCurrent() {
	    return pageCurrent;
	}

	public void setPageCurrent(Integer pageCurrent) {	    	
	    this.pageCurrent = pageCurrent;
    }
}
