package com.permission.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.permission.common.orm.PageInfo;
import com.permission.mapping.ModuleMapper;
import com.permission.mapping.RelevanceMapper;
import com.permission.model.bo.ModuleBO;
import com.permission.model.vo.ModuleView;
import com.permission.pojo.Module;
import com.permission.pojo.Relevance;
import com.permission.service.IModuleManagerService;

@Service
public class ModuleManagerServiceImpl implements IModuleManagerService {

	@Resource
	private ModuleMapper _moduleDao;
	@Resource
	private RelevanceMapper _relevanceDao;

	@Override
	public ModuleBO Load(int parentId, int pageindex, int pagesize) {
		// TODO Auto-generated method stub
		List<Module> Modules = null;
		int total = 0;
		if (parentId == 0){
			PageInfo page=new PageInfo(pageindex,pagesize);
			Modules = _moduleDao.LoadModuleListPage(page);
			total = page.getTotalResult();
		}
		else{
			PageInfo page=new PageInfo(pageindex,pagesize);
			Modules = _moduleDao.LoadInOrgListPage(page, GetSubOrgIds(parentId));
			total = page.getTotalResult();
		}
		ModuleBO moduleBO=new ModuleBO();
		moduleBO.setTotal(total);
		moduleBO.setList(Modules);
		moduleBO.setPageCurrent(pageindex);
		return moduleBO;
	}

	@Override
	public List<Module> LoadForTree() {
		// TODO Auto-generated method stub
		return _moduleDao.FindAll();
	}

	@Override
	public List<ModuleView> LoadByParent(int parentId) {
		// TODO Auto-generated method stub
		List<ModuleView> modules = new ArrayList<ModuleView>();
		List<Module> roots = _moduleDao.LoadByParent(parentId);
		for(Module module : roots){
			ModuleView mv=new ModuleView();
			mv.setId(module.getId());
			mv.setName(module.getName());
			mv.setUrl(module.getUrl());
			mv.setParentid(module.getParentid());
			mv.setIconname(module.getIconname());
			mv.setChildern(LoadByParent(module.getId()));
			modules.add(mv);
		}
		return modules;
	}

	@Override
	public Module Find(int id) {
		// TODO Auto-generated method stub
		Module module = _moduleDao.selectByPrimaryKey(id);
		if (module == null) {
			module = new Module();
		}
		return module;
	}

	@Override
	public void Delete(int id) {
		// TODO Auto-generated method stub
		_moduleDao.deleteByPrimaryKey(id);
	}

	@Override
	public void AddOrUpdate(Module vm) throws Exception {
		// TODO Auto-generated method stub
		if (vm.getId() == 0){
			ChangeModuleCascade(vm);
			_moduleDao.insert(vm);
		}
		else{
			_moduleDao.updateByPrimaryKey(vm);
		}
	}

	@Override
	public List<Module> LoadForUser(int userId) {
		// TODO Auto-generated method stub
		List<Integer> userRoleIds =_relevanceDao.FindUserRoleIds(userId);
		List<Integer> moduleIds = _relevanceDao.FindSecondIds(userId,"UserModule" ,"RoleModule" ,userRoleIds);
		if (moduleIds==null || moduleIds.size()==0) {
			return new ArrayList<Module>();
		}
		return _moduleDao.Find(moduleIds);
	}

	@Override
	public void AssignModuleForUser(int userId, Integer[] ids) {
		// TODO Auto-generated method stub
		List<Integer> ids1=new ArrayList<Integer>();
		ids1.add(userId);
		_relevanceDao.deleteByKeyAndFirstIds("UserModule", ids1);
		for (int secondid : ids) {
			Relevance relevance=new Relevance();
			relevance.setKey("UserModule");
			relevance.setFirstid(userId);
			relevance.setSecondid(secondid);
			relevance.setOperatetime(new Date());
			_relevanceDao.insert(relevance);
		}

	}

	@Override
	public List<Module> LoadForRole(int roleId) {
		// TODO Auto-generated method stub
		List<Integer> moduleIds =_relevanceDao.FindRoleIds(roleId);
		if (moduleIds==null || moduleIds.size()==0) {
			return new ArrayList<Module>();		
		}
		return _moduleDao.Find(moduleIds);
	}

	@Override
	public void AssignModuleForRole(int roleId, Integer[] ids) {
		// TODO Auto-generated method stub
		List<Integer> ids1=new ArrayList<Integer>();
		ids1.add(roleId);
		_relevanceDao.deleteByKeyAndFirstIds("RoleModule", ids1);
		for (int secondid : ids) {
			Relevance relevance=new Relevance();
			relevance.setKey("RoleModule");
			relevance.setFirstid(roleId);
			relevance.setSecondid(secondid);
			relevance.setOperatetime(new Date());
			_relevanceDao.insert(relevance);
		}
	}


	/**
	 * 根据同一级中最大的语义ID
	 * @param parentId
	 * @return
	 */
	private List<Integer> GetSubOrgIds(int parentId){
		Module parent =_moduleDao.selectByPrimaryKey(parentId);
		if (parent==null) {
			return null;
		}
		List<Integer> orgs =_moduleDao.FindOrgs(parent.getCascadeid());
		return orgs;
	}


	/**
	 * 修改对象的级联ID
	 * @throws Exception 
	 */
	private void ChangeModuleCascade(Module module) throws Exception{
		String cascadeId;
		int currentCascadeId = 1;  //当前结点的级联节点最后一位
		List<Module> sameLevels=_moduleDao.SameLevels(module.getParentid(), module.getId());
		for (Module obj : sameLevels) {
			String[] arrStrings= obj.getCascadeid().split(",");
			int objCascadeId = Integer.getInteger(arrStrings[arrStrings.length-1]);
			if (currentCascadeId <= objCascadeId) currentCascadeId = objCascadeId + 1;
		}

		if (module.getParentid() != 0)
		{
			Module parentOrg = _moduleDao.selectByPrimaryKey(module.getParentid());
			if (parentOrg != null)
			{
				cascadeId = parentOrg.getCascadeid() + "." + currentCascadeId;
				module.setParentname(parentOrg.getParentname()); 
			}
			else
			{
				throw new Exception("未能找到该组织的父节点信息");
			}
		}
		else
		{
			cascadeId = "0." + currentCascadeId;
			module.setParentname("根节点");
		}
		module.setCascadeid(cascadeId);
	}

}
