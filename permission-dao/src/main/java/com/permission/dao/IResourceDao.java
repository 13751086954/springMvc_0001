package com.permission.dao;

import java.util.List;
import java.util.Map;

import com.permission.pojo.Resource;

public interface IResourceDao {

     Map<String, Object> LoadInOrgs(Integer pageindex, Integer pagesize, List<Integer> orgIds);
    
     void Delete(Integer id);
  
     List<Resource> LoadResources(Integer pageindex, Integer pagesize);
   
     List<Resource> Find(List<Integer>  resourceIds);
     
     List<Resource> FindAll();
}
