package com.permission.dao;

import java.util.List;

import com.permission.pojo.ModuleElement;

public interface IModuleElementDao {

	List<ModuleElement> Find(Integer moduleId,List<Integer>elementIds);
	
	List<ModuleElement> FindByModuleId(Integer moduleId);
}
