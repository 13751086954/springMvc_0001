package com.permission.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.permission.common.orm.PageInfo;
import com.permission.dao.IStockDao;
import com.permission.mapping.StockMapper;
import com.permission.pojo.Stock;

@Repository
public class StockDaoImpl implements IStockDao {

	@Resource
	StockMapper _stockMapper;
	
	public Map<String, Object> LoadInOrgs(Integer pageindex, Integer pagesize,
			List<Integer> orgIds) {
		// TODO Auto-generated method stub
		int currentPage = pageindex;
	    int pageSize = 3;
	    if (currentPage<=0){
	         currentPage =1;
	     }
	    int currentResult = (currentPage-1) * pageSize;	               
	    PageInfo page = new PageInfo();
	    page.setShowCount(pageSize);
	    page.setCurrentResult(currentResult);
	    List<Stock> stocks= _stockMapper.LoadInOrgs(page,orgIds);
	    Map<String, Object> map=new HashMap<String, Object>();
	    map.put("rows", stocks);
	    map.put("total ",page.getTotalResult());
		return map;
	}

	public void Delete(Integer id) {
		// TODO Auto-generated method stub
		_stockMapper.deleteByPrimaryKey(id);
	}

	public List<Stock> LoadStocks(Integer pageindex, Integer pagesize) {
		// TODO Auto-generated method stub
		int currentPage = pageindex;
	    int pageSize = 3;
	    if (currentPage<=0){
	         currentPage =1;
	     }
	    int currentResult = (currentPage-1) * pageSize;	               
	    PageInfo page = new PageInfo();
	    page.setShowCount(pageSize);
	    page.setCurrentResult(currentResult);
	    List<Stock> stocks= _stockMapper.LoadStocks(page);
		return stocks;
	}

}
