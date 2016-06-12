package com.permission.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import com.permission.common.orm.PageInfo;
import com.permission.mapping.OrgMapper;
import com.permission.mapping.RelevanceMapper;
import com.permission.mapping.RoleMapper;
import com.permission.model.bo.RoleBO;
import com.permission.model.vo.RoleVM;
import com.permission.pojo.Org;
import com.permission.pojo.Relevance;
import com.permission.pojo.Role;
import com.permission.service.IRoleManagerService;

@Service
public class RoleManagerServiceImpl implements IRoleManagerService {

	@javax.annotation.Resource
	RoleMapper _roleDao;
	@javax.annotation.Resource
	OrgMapper _orgDao;
	@javax.annotation.Resource
	RelevanceMapper _relevanceDao;
	
	Mapper _mapper ;

	private  RoleManagerServiceImpl() {

		_mapper=new DozerBeanMapper();	
	}

	@Override
	public Integer GetRoleCntInOrg(Integer orgId) {
		// TODO Auto-generated method stub
		if (orgId == 0) {
			return _roleDao.GetRoleCntInOrgs(null);
		}
		else{
			return _roleDao.GetRoleCntInOrgs(GetSubOrgIds(orgId));
		}
	}

	@Override
	public RoleBO Load(Integer orgId, Integer pageindex, Integer pagesize) {
		// TODO Auto-generated method stub
		if (pageindex < 1) pageindex = 1;
		List<Role> roles = null;
		Integer total = 0;
		if (orgId == 0){
			PageInfo page=new PageInfo(pageindex,pagesize);
			roles = _roleDao.LoadInOrgListPage(page,null);
			total = page.getTotalPage();
		}
		else{
			PageInfo page=new PageInfo(pageindex,pagesize);
			roles = _roleDao.LoadInOrgListPage(page, GetSubOrgIds(orgId));
			total = page.getTotalPage();
		}
		RoleBO roleBO = new RoleBO();
		roleBO.setTotal(total);
		roleBO.setList(roles);
		roleBO.setPageCurrent(pageindex);
		return roleBO;
	}

	@Override
	public List<Integer> GetSubOrgIds(Integer orgId) {
		// TODO Auto-generated method stub
		Org org = _orgDao.selectByPrimaryKey(orgId);
		return _orgDao.GetSubOrgIds(org.getCascadeid(),null);
	}

	@Override
	public Role Find(Integer id) {
		// TODO Auto-generated method stub
		return _roleDao.selectByPrimaryKey(id);
	}

	@Override
	public void Delete(Integer id) {
		// TODO Auto-generated method stub
		_roleDao.deleteByPrimaryKey(id);
	}

	@Override
	public void AddOrUpdate(Role role) {
		// TODO Auto-generated method stub
		if (role.getId() == 0){
			_roleDao.insert(role);
		}
		else{
			_roleDao.updateByPrimaryKey(role);
		}
	}

	@Override
	public List<RoleVM> LoadForOrgAndUser(Integer orgId, Integer userId) {
		// TODO Auto-generated method stub
		List<Role> roleIds = _roleDao.FindByOrgId(orgId);
		List<RoleVM>  roleVMs =new ArrayList<RoleVM>();
		for (Role role : roleIds) {
			RoleVM rolevm= (RoleVM) _mapper.map(role,RoleVM.class);
			Relevance accesseType = _relevanceDao.FindAccesseType("UserRole", userId, role.getId());
			rolevm.setIsBelongUser((accesseType!=null));
			roleVMs.add(rolevm);
		}
		return roleVMs;
	}

	@Override
	public void AccessRole(Integer userId, List<Integer> roleIds) {
		// TODO Auto-generated method stub
		_relevanceDao.deleteByKeyAndFirstIds("UserRole", roleIds);

		for (Integer menuId : roleIds) {
			Relevance relevance= new Relevance();
			relevance.setKey("UserRole");
			relevance.setFirstid(userId);
			relevance.setSecondid(menuId);
			relevance.setOperatetime(new Date());
			_relevanceDao.insert(relevance);
		}
	}

}
