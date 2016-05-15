package com.permission.dao;

import java.util.List;
import java.util.Map;

import com.permission.pojo.User;

public interface IUserDao {

	Map<String, Object> LoadUsers(Integer pageindex, Integer pagesize);
	     
	Map<String, Object> LoadInOrgs(Integer pageindex, Integer pagesize, List<Integer> orgIds);
	
	User FindSingle(String account);

}
