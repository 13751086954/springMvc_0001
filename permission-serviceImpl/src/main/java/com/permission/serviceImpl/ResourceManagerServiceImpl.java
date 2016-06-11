package com.permission.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.permission.common.orm.PageInfo;
import com.permission.mapping.CategoryMapper;
import com.permission.mapping.RelevanceMapper;
import com.permission.mapping.ResourceMapper;
import com.permission.model.bo.ResourceBO;
import com.permission.model.vo.ResourceVM;
import com.permission.pojo.Category;
import com.permission.pojo.Relevance;
import com.permission.pojo.Resource;
import com.permission.service.IResourceManagerService;

public class ResourceManagerServiceImpl implements IResourceManagerService {
   
	@javax.annotation.Resource
	ResourceMapper _resourceDao;
	@javax.annotation.Resource
	CategoryMapper _categoryDao;
	@javax.annotation.Resource
	RelevanceMapper _relevanceDao;
	
	@Override
	public Integer GetResourceCntInOrg(Integer orgId) {
		// TODO Auto-generated method stub
		 if (orgId == 0){
             return _resourceDao.GetResourceCntInOrgs(null);
         }
         else{
             return _resourceDao.GetResourceCntInOrgs(GetSubOrgIds(orgId));
         }
	}

	@Override
	public ResourceBO Load(Integer categoryId, Integer pageindex,
			Integer pagesize) {
		// TODO Auto-generated method stub
		List<Resource> Resources = null;
		Integer total = 0;
		if (categoryId == 0){
			PageInfo page=new PageInfo(pageindex,pagesize);
			Resources = _resourceDao.LoadInOrgListPage(page,null);
			total = page.getTotalPage();
		}
		else{
			PageInfo page=new PageInfo(pageindex,pagesize);
			Resources = _resourceDao.LoadInOrgListPage(page, GetSubOrgIds(categoryId));
			total = page.getTotalPage();
		}
		ResourceBO resourceBO=new ResourceBO();
		resourceBO.setTotal(total);
		resourceBO.setList(Resources);
		resourceBO.setPageCurrent(pageindex);
		return resourceBO;
	}

	@Override
	public List<Resource> LoadAll() {
		// TODO Auto-generated method stub
		return _resourceDao.FindAll();
	}

	@Override
	public List<Integer> GetSubOrgIds(Integer orgId) {
		// TODO Auto-generated method stub
		Category org= _categoryDao.selectByPrimaryKey(orgId);
		List<Integer> orgs =_categoryDao.GetSubOrgIds(org.getCascadeid());
		return orgs;
	}

	@Override
	public Resource Find(Integer id) {
		// TODO Auto-generated method stub
		return _resourceDao.selectByPrimaryKey(id);
	}

	@Override
	public void Delete(Integer id) {
		// TODO Auto-generated method stub
		_resourceDao.deleteByPrimaryKey(id);
	}

	@Override
	public void AddOrUpdate(Resource model) {
		// TODO Auto-generated method stub
		 if (model.getId() == 0){
			 _resourceDao.insert(model);
         }
         else{
        	 _resourceDao.updateByPrimaryKey(model);
         }
	}

	@Override
	public List<ResourceVM> LoadWithAccess(String accessType, Integer firstId,
			Integer cId) {
		// TODO Auto-generated method stub
		List<ResourceVM> listVms =new ArrayList<ResourceVM>();
		List<Resource> resources = _resourceDao.LoadInOrgs(new ArrayList<Integer>(cId));
		for (Resource resource : resources) {
			Relevance accessed = _relevanceDao.FindAccesseType(accessType, firstId, resource.getId());
			ResourceVM resourceVM = new ResourceVM();
			resourceVM.setId(resource.getId());
			resourceVM.setName(resource.getName());
			resourceVM.setIsBelongUser((accessed != null));
			resourceVM.setDescription(resource.getDescription());
			resourceVM.setKey(resource.getKey());
			resourceVM.setStatus(resource.getStatus());
			listVms.add(resourceVM);
		}
		return listVms;
	}

	@Override
	public void AssignResForUser(Integer userId, List<Integer> resIds) {
		// TODO Auto-generated method stub
		_relevanceDao.deleteByKeyAndFirstIds("UserResource", resIds);

		for (Integer menuId : resIds) {
			Relevance relevance= new Relevance();
			relevance.setKey("UserResource");
			relevance.setFirstid(userId);
			relevance.setSecondid(menuId);
			relevance.setOperatetime(new Date());
			_relevanceDao.insert(relevance);
		}
	}

	@Override
	public void AssignResForRole(Integer roleId, List<Integer> resIds) {
		// TODO Auto-generated method stub
		_relevanceDao.deleteByKeyAndFirstIds("RoleResource", resIds);

		for (Integer menuId : resIds) {
			Relevance relevance= new Relevance();
			relevance.setKey("RoleResource");
			relevance.setFirstid(roleId);
			relevance.setSecondid(menuId);
			relevance.setOperatetime(new Date());
			_relevanceDao.insert(relevance);
		}
	}

	
}
