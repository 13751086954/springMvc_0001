package com.permission.model.bo;

import java.util.List;

import com.permission.pojo.Stock;

public class StockBO {

private Integer total;
	
	private List<Stock> list;
	
	private Integer pageCurrent;
	
	public  Integer getTotal() {
	    return total;
	}

	public void setTotal(Integer total) {	    	
	    this.total = total;
    }
	
	public  List<Stock> getList() {
	    return list;
	}

	public void setList(List<Stock> list) {	    	
	    this.list = list;
    }
	
	public  Integer getPageCurrent() {
	    return pageCurrent;
	}

	public void setPageCurrent(Integer pageCurrent) {	    	
	    this.pageCurrent = pageCurrent;
    }
}
