package com.permission.dao;

import java.util.List;
import java.util.Map;

import com.permission.pojo.Stock;


public interface IStockDao {

	 Map<String, Object> LoadInOrgs(Integer pageindex, Integer pagesize, List<Integer> orgIds);
	    
     void Delete(Integer id);
  
     List<Stock> LoadStocks(Integer pageindex, Integer pagesize);
}
