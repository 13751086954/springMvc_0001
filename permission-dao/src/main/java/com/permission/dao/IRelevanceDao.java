package com.permission.dao;

import java.util.List;
import java.util.Map;

public interface IRelevanceDao {
	 void DeleteBy(String key, Integer... firstIds);
	 
     void AddRelevance(String key, Map<Integer, Integer>  idMaps);
     
     List<Integer> FindUserRoleIds(Integer firstId);
     
     List<Integer> FindModuleIds(Integer firstId,List<Integer> ids);
     
     List<Integer> FindElementIds(Integer firstId,List<Integer> ids);
     
     List<Integer>  FindResourceIds(Integer firstId,List<Integer> ids);
     
     List<Integer>  FindOrgids(Integer firstId,List<Integer> ids);
}
