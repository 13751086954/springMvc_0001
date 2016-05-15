package com.permission.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.permission.common.orm.PageInfo;
import com.permission.dao.IRoleDao;
import com.permission.mapping.RoleMapper;
import com.permission.pojo.Role;

@Repository
public class RoleDaoImpl implements IRoleDao {

	@Resource
	RoleMapper _roleMapper;
	
	public List<Role> LoadRoles(Integer pageindex, Integer pagesize) {
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
	    List<Role> roles= _roleMapper.LoadRoleListPage(page);
	    return roles;
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
	    List<Role> roles= _roleMapper.LoadInOrgListPage(page,orgIds);
	    Map<String, Object> map=new HashMap<String, Object>();
	    map.put("rows", roles);
	    map.put("total ",page.getTotalResult());
		return map;
	}

	public void Delete(Integer id) {
		// TODO Auto-generated method stub
		_roleMapper.deleteByPrimaryKey(id);
	}

}
