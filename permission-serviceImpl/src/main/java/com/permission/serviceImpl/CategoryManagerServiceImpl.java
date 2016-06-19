package com.permission.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.permission.common.orm.PageInfo;
import com.permission.mapping.CategoryMapper;
import com.permission.mapping.OrgMapper;
import com.permission.model.bo.CategoryBO;
import com.permission.pojo.Category;
import com.permission.pojo.Org;
import com.permission.service.ICategoryManagerService;

@Service
public class CategoryManagerServiceImpl implements ICategoryManagerService {

	@Resource
	CategoryMapper _categoryDao;
	@Resource
	OrgMapper _orgDao;

	@Override
	public int GetCategoryCntInOrg(int orgId) {
		// TODO Auto-generated method stub
		if (orgId == 0){
			return _categoryDao.GetCategoryCntInOrg(null);
		}
		else{
			return _categoryDao.GetCategoryCntInOrg(GetSubOrgIds(orgId));
		}
	}

	/**
	 * /修改对象的级联ID，生成类似XXX.XXX.X.XX
	 * @param org
	 * @throws Exception
	 */
	private void ChangeModuleCascade(Category org) throws Exception{
		String cascadeId;
		int currentCascadeId = 1;  //当前结点的级联节点最后一位
		List<Category> sameLevels = _categoryDao.SameLevels(org.getParentid(), org.getId());
		for (Category obj : sameLevels) {
			String[] arrStrings= obj.getCascadeid().split(",");
			int objCascadeId=Integer.getInteger(arrStrings[arrStrings.length-1]);
			if (currentCascadeId <= objCascadeId) currentCascadeId = objCascadeId + 1;
		}

		if (org.getParentid() != 0){
			Category parentOrg = _categoryDao.selectByPrimaryKey(org.getParentid());

			if (parentOrg != null){
				cascadeId = parentOrg.getCascadeid() + "." + currentCascadeId;
			}
			else {
				throw new Exception("未能找到该组织的父节点信息");
			}
		}
		else{
			cascadeId = "0." + currentCascadeId;
		}
		org.setCascadeid(cascadeId);
	}

	@Override
	public List<Category> LoadAll() {
		// TODO Auto-generated method stub
		return _categoryDao.LoadAll();
	}

	@Override
	public CategoryBO Load(int orgId, int pageindex, int pagesize) {
		// TODO Auto-generated method stub
		List<Category> categories = null;
		int total = 0;
		if (orgId == 0){
			PageInfo page=new PageInfo(pageindex,pagesize);
			categories = _categoryDao.LoadInOrgListPage(page,null);
			total = page.getTotalPage();
		}
		else{
			PageInfo page=new PageInfo(pageindex,pagesize);
			categories = _categoryDao.LoadInOrgListPage(page, GetSubOrgIds(orgId));
			total = page.getTotalPage();
		}
		CategoryBO categoryBO=new CategoryBO();
		categoryBO.setTotal(total);
		categoryBO.setList(categories);
		categoryBO.setPageCurrent(pageindex);
		return categoryBO;
	}

	@Override
	public List<Integer> GetSubOrgIds(int orgId) {
		// TODO Auto-generated method stub
		Org org = _orgDao.selectByPrimaryKey(orgId);
		return _orgDao.GetSubOrgIds(org.getCascadeid(), 0);
	}

	@Override
	public Category Find(int id) {
		// TODO Auto-generated method stub
		return _categoryDao.selectByPrimaryKey(id);
	}

	@Override
	public void Delete(int id) {
		// TODO Auto-generated method stub
		_categoryDao.deleteByPrimaryKey(id);
	}

	@Override
	public void AddOrUpdate(Category model) throws Exception {
		// TODO Auto-generated method stub
		if (model.getId() == 0) {
			ChangeModuleCascade(model);
			_categoryDao.insert(model);
		}
		else {
			_categoryDao.updateByPrimaryKey(model);
		}
	}

}
