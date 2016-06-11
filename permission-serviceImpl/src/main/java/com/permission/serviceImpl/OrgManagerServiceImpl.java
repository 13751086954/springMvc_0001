package com.permission.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.permission.mapping.OrgMapper;
import com.permission.mapping.RelevanceMapper;
import com.permission.pojo.Category;
import com.permission.pojo.Org;
import com.permission.pojo.Relevance;
import com.permission.service.IOrgManagerService;

public class OrgManagerServiceImpl implements IOrgManagerService {

	@Resource
	private OrgMapper _orgDao;
	@Resource
	private RelevanceMapper _relevanceDao;


	@Override
	public List<Org> GetAll() {
		// TODO Auto-generated method stub
		return _orgDao.LoadOrgs();
	}

	@Override
	public List<Org> LoadDirectChildren(Integer orgId) {
		// TODO Auto-generated method stub
		return _orgDao.LoadByParentId(orgId);
	}

	@Override
	public List<Org> LoadAllChildren(Integer orgId) throws Exception {
		// TODO Auto-generated method stub
		String cascadeId = "0.";
		if (orgId != 0){
			Org org = _orgDao.selectByPrimaryKey(orgId);
			if (org == null)
				throw new Exception("未能找到指定对象信息");
			cascadeId = org.getCascadeid();
		}
		return _orgDao.GetSubOrgs(cascadeId, orgId);
	}

	@Override
	public Integer AddOrUpdate(Org org) throws Exception {
		// TODO Auto-generated method stub
		if (org.getId() == 0) {
			ChangeModuleCascade(org);
			org.setId(_orgDao.insert(org));
		}
		else{
			_orgDao.updateByPrimaryKeyWithBLOBs(org);
		}

		return org.getId();
	}

	@Override
	public void DelOrg(Integer id) {
		// TODO Auto-generated method stub
		Org delOrg = _orgDao.selectByPrimaryKey(id);
		if (delOrg == null) return;
		_orgDao.deleteByCascadeId(delOrg.getCascadeid());
	}

	@Override
	public List<Org> LoadForUser(Integer userId) {
		// TODO Auto-generated method stub
		//用户角色
		List<Integer> userRoleIds = _relevanceDao.FindUserRoleIds(userId);
		List<Integer> moduleIds =_relevanceDao.FindSecondIds(userId, "UserAccessedOrg", "RoleAccessedOrg", userRoleIds);
		if (moduleIds==null || moduleIds.size()==0) {
			return new ArrayList<Org>();
		}
		return _orgDao.Find(moduleIds);
	}

	@Override
	public void AssignModuleForUser(Integer userId, List<Integer> ids) {
		// TODO Auto-generated method stub
		List<Integer> ids1=new ArrayList<Integer>();
		ids1.add(userId);
		_relevanceDao.deleteByKeyAndFirstIds("UserAccessedOrg", ids1);
		for (Integer secondid : ids) {
			Relevance relevance=new Relevance();
			relevance.setKey("UserAccessedOrg");
			relevance.setFirstid(userId);
			relevance.setSecondid(secondid);
			relevance.setOperatetime(new Date());
			_relevanceDao.insert(relevance);
		}
	}

	@Override
	public List<Org> LoadForRole(Integer roleId) {
		// TODO Auto-generated method stub
		List<Integer> moduleIds =_relevanceDao.FindSecondIds(roleId, "UserAccessedOrg", "", null);
		if (moduleIds==null || moduleIds.size()==0) {
			return new ArrayList<Org>();
		}
		return _orgDao.Find(moduleIds);
	}

	@Override
	public void AssignModuleForRole(Integer roleId, List<Integer> ids) {
		// TODO Auto-generated method stub
		List<Integer> ids1=new ArrayList<Integer>();
		ids1.add(roleId);
		_relevanceDao.deleteByKeyAndFirstIds("RoleAccessedOrg", ids1);
		for (Integer secondid : ids) {
			Relevance relevance=new Relevance();
			relevance.setKey("RoleAccessedOrg");
			relevance.setFirstid(roleId);
			relevance.setSecondid(secondid);
			relevance.setOperatetime(new Date());
			_relevanceDao.insert(relevance);
		}
	}

	/**
	 * /修改对象的级联ID，生成类似XXX.XXX.X.XX
	 * @param org
	 * @throws Exception
	 */
	private void ChangeModuleCascade(Org org) throws Exception{
		String cascadeId;
		Integer currentCascadeId = 1;  //当前结点的级联节点最后一位
		List<Category> sameLevels = _orgDao.SameLevels(org.getParentid(), org.getId());
		for (Category obj : sameLevels) {
			String[] arrStrings= obj.getCascadeid().split(",");
			Integer objCascadeId=Integer.getInteger(arrStrings[arrStrings.length-1]);
			if (currentCascadeId <= objCascadeId) currentCascadeId = objCascadeId + 1;
		}

		if (org.getParentid() != 0){
			Org parentOrg = _orgDao.selectByPrimaryKey(org.getParentid());

			if (parentOrg != null){
				cascadeId = parentOrg.getCascadeid() + "." + currentCascadeId;
				org.setParentname(parentOrg.getParentname());
			}
			else {
				throw new Exception("未能找到该组织的父节点信息");
			}
		}
		else{
			cascadeId = "0." + currentCascadeId;
			org.setParentname("根节点");
		}
		org.setCascadeid(cascadeId);
	}

}
