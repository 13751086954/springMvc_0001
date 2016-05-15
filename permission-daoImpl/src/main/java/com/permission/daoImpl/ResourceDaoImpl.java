package com.permission.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.permission.common.orm.PageInfo;
import com.permission.dao.IResourceDao;
import com.permission.mapping.ResourceMapper;
import com.permission.pojo.Resource;

@Repository
public class ResourceDaoImpl implements IResourceDao {

	@javax.annotation.Resource
	ResourceMapper _resourceMapper;
	
	public List<Resource> LoadResources(Integer pageindex, Integer pagesize) {
		// TODO Auto-generated method stub
		int currentPage = pageindex;
	    int pageSize = 3;
	    if (currentPage<=0){
	         currentPage =1;
	     }
	    int currentResult = (currentPage-1) * pageSize;	               
	    PageInfo page = new PageInfo();
	    page.setShowCount(pageSize);
	    page.setCurrentResult(currentResult);
	    List<Resource> resources= _resourceMapper.LoadResourceListPage(page);
		return resources;
	}
	
	public Map<String, Object> LoadInOrgs(Integer pageindex, Integer pagesize,
			List<Integer> orgIds) {
		// TODO Auto-generated method stub
		int currentPage = pageindex;
	    int pageSize = 3;
	    if (currentPage<=0){
	         currentPage =1;
	     }
	    int currentResult = (currentPage-1) * pageSize;	               
	    PageInfo page = new PageInfo();
	    page.setShowCount(pageSize);
	    page.setCurrentResult(currentResult);
	    List<Resource> resources= _resourceMapper.LoadInOrgListPage(page,orgIds);
	    Map<String, Object> map=new HashMap<String, Object>();
	    map.put("rows", resources);
	    map.put("total ",page.getTotalResult());
		return map;
	}

	public void Delete(Integer id) {
		// TODO Auto-generated method stub
		_resourceMapper.deleteByPrimaryKey(id);
	}

	public List<Resource> Find(List<Integer> resourceIds) {
		// TODO Auto-generated method stub
		return _resourceMapper.Find(resourceIds);
	}

	public List<Resource> FindAll() {
		// TODO Auto-generated method stub
		return _resourceMapper.FindAll();
	}



}
