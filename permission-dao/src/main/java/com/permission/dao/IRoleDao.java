package com.permission.dao;

import java.util.List;
import java.util.Map;

import com.permission.pojo.Role;

public interface IRoleDao {

	  List<Role> LoadRoles(Integer pageindex, Integer pagesize);
     
	  Map<String, Object> LoadInOrgs(Integer pageindex, Integer pagesize, List<Integer> orgIds);
     
      void Delete(Integer id);
   
}
