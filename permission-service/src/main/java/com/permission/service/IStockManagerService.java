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
	public StockBO Load(int orgId, int pageindex, int pagesize) throws Exception;

	public Stock Find(int id);

	public void Delete(int id);

	public void AddOrUpdate(Stock model);

}
