package com.permission.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.permission.mapping.ModuleElementMapper;
import com.permission.mapping.ModuleMapper;
import com.permission.mapping.RelevanceMapper;
import com.permission.model.vo.ModuleElementVM;
import com.permission.pojo.ModuleElementWithBLOBs;
import com.permission.pojo.Relevance;
import com.permission.service.IModuleElementManagerService;

@Service("moduleElementManagerService") 
public class ModuleElementManagerServiceImpl implements IModuleElementManagerService {

	@Resource
	private ModuleMapper _moduleDao;
	@Resource
	private ModuleElementMapper _moduleElementDao;
	@Resource
	private RelevanceMapper _relevanceDao;

	@Override
	public void AddOrUpdate(ModuleElementWithBLOBs model) {
		// TODO Auto-generated method stub
		if (model.getId() == 0) {
			_moduleElementDao.insert(model);
		}
		else{
			_moduleElementDao.updateByPrimaryKey(model);
		}
	}

	@Override
	public List<ModuleElementWithBLOBs> LoadByModuleId(int id) {
		// TODO Auto-generated method stub
		return _moduleElementDao.FindByModuleId(id);
	}

	@Override
	public List<ModuleElementVM> LoadWithAccess(String accessType,
			int firstId, int moduleId) {
		// TODO Auto-generated method stub
		List<ModuleElementVM> listVms = new ArrayList<ModuleElementVM>();
		if (moduleId == 0) return listVms;
		String modulename = _moduleDao.selectByPrimaryKey(moduleId).getName();
		List<ModuleElementWithBLOBs> elements = LoadByModuleId(moduleId);
		for (ModuleElementWithBLOBs element : elements) {
			Relevance accessed = _relevanceDao.FindAccesseType(accessType, firstId, element.getId());
			ModuleElementVM vm = new ModuleElementVM();
			vm.setId(element.getId());
			vm.setName(element.getName());
			vm.setModuleid(element.getModuleid());
			vm.setDomid(element.getDomid());
			vm.setModuleName(modulename);
			vm.setAccessed((accessed != null));
			listVms.add(vm);
		}
		return listVms;
	}

	@Override
	public void Delete(int id) {
		// TODO Auto-generated method stub
		_moduleElementDao.deleteByPrimaryKey(id);
	}

	@Override
	public void AssignForRole(int roleId, int moduleId, List<Integer> menuIds) {	
		// TODO Auto-generated method stub
		List<Integer> elements = _moduleElementDao.FindIdsByModuleId(moduleId);
		_relevanceDao.deleteByKeyAndFirstIdAndSecondIds("RoleElement", roleId,elements);

		for (int menuId : menuIds) {
			Relevance relevance= new Relevance();
			relevance.setKey("RoleElement");
			relevance.setFirstid(roleId);
			relevance.setSecondid(menuId);
			relevance.setOperatetime(new Date());
			_relevanceDao.insert(relevance);
		}

	}

	@Override
	public void AssignForUser(int userId, int moduleId, List<Integer> ids) {
		// TODO Auto-generated method stub
		List<Integer> elements = _moduleElementDao.FindIdsByModuleId(moduleId);
		_relevanceDao.deleteByKeyAndFirstIdAndSecondIds("UserElement", userId,elements);

		for (int id : ids) {
			Relevance relevance= new Relevance();
			relevance.setKey("UserElement");
			relevance.setFirstid(userId);
			relevance.setSecondid(id);
			relevance.setOperatetime(new Date());
			_relevanceDao.insert(relevance);
		}
	}


}
