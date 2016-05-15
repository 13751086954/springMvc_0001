package com.permission.dao;


import java.util.List;
import java.util.Map;

import com.permission.pojo.Module;

public interface IModuleDao {

	Map<String, Object> LoadModules(Integer pageindex, Integer pagesize);

	Map<String, Object> LoadInOrgs(int pageindex, int pagesize,  List<Integer> orgIds); 
	
    void Delete(int id);
    
    List<Module> Find(List<Integer> moduleIds);
    
    List<Module> FindAll();
     
}
