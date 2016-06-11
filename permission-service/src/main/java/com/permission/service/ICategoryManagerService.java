package com.permission.service;

import java.util.List;
import com.permission.model.bo.CategoryBO;
import com.permission.pojo.Category;

public interface ICategoryManagerService {

	public Integer GetCategoryCntInOrg(Integer orgId);

	public List<Category> LoadAll();

	public CategoryBO Load(Integer orgId, Integer pageindex, Integer pagesize);

	public List<Integer> GetSubOrgIds(Integer orgId);

	public Category Find(Integer id);

	public void Delete(Integer id);

	public void AddOrUpdate(Category model) throws Exception;

}
