package com.permission.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.permission.dao.IModuleElementDao;
import com.permission.mapping.ModuleElementMapper;
import com.permission.pojo.ModuleElement;

@Repository
public class ModuleElementDaoImpl implements IModuleElementDao {

	@Resource
	ModuleElementMapper _moduleElementMapper;
	
	public List<ModuleElement> Find(Integer moduleId, List<Integer> elementIds) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("moduleId", moduleId);
        params.put("ids", elementIds);
		return _moduleElementMapper.Find(params);
	}

	public List<ModuleElement> FindByModuleId(Integer moduleId) {
		// TODO Auto-generated method stub
		return _moduleElementMapper.FindByModuleId(moduleId);
	}

}
