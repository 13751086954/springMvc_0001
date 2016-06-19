package com.permission.model.bo;

import java.util.List;

import com.permission.pojo.Stock;

public class StockBO {

private int total;
	
	private List<Stock> list;
	
	private int pageCurrent;
	
	public  int getTotal() {
	    return total;
	}

	public void setTotal(int total) {	    	
	    this.total = total;
    }
	
	public  List<Stock> getList() {
	    return list;
	}

	public void setList(List<Stock> list) {	    	
	    this.list = list;
    }
	
	public  int getPageCurrent() {
	    return pageCurrent;
	}

	public void setPageCurrent(int pageCurrent) {	    	
	    this.pageCurrent = pageCurrent;
    }
}
