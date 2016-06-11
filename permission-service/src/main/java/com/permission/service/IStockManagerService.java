package com.permission.service;

import com.permission.model.bo.StockBO;
import com.permission.pojo.Stock;

public interface IStockManagerService {

	/**
	 * 根据部门ID得到进出库信息
	 * @param orgId
	 * @param pageindex
	 * @param pagesize
	 * @return
	 * @throws Exception 
	 */
	public StockBO Load(Integer orgId, Integer pageindex, Integer pagesize) throws Exception;

	public Stock Find(Integer id);

	public void Delete(Integer id);

	public void AddOrUpdate(Stock model);

}
