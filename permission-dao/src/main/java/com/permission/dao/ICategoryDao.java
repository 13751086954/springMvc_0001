package com.permission.dao;

import java.util.List;
import java.util.Map;

import com.permission.pojo.Category;

public interface ICategoryDao {
	
	 List<Category> LoadCategorys(Integer pageindex, Integer pagesize);
   
     Map<String, Object> LoadInOrgs(Integer pageindex, Integer pagesize,Integer... orgIds);
 
     void Delete(Integer id);  
}
