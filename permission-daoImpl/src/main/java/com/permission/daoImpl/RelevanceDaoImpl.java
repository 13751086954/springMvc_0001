package com.permission.daoImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.permission.dao.IRelevanceDao;
import com.permission.mapping.RelevanceMapper;
import com.permission.pojo.Relevance;

@Repository
public class RelevanceDaoImpl implements IRelevanceDao {

	@Resource
	private RelevanceMapper _relevanceMapper;
	
	public void DeleteBy(String key, Integer... firstIds) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("key", key);
        params.put("ids", firstIds);
		_relevanceMapper.deleteByKeyAndFirstIds(params);	
	}

	public void AddRelevance(String key, Map<Integer, Integer> idMaps) {
		// TODO Auto-generated method stub
		for(Map.Entry<Integer, Integer> entry:idMaps.entrySet()){    
		     System.out.println(entry.getKey()+"--->"+entry.getValue());		     
		     Relevance relevance=new  Relevance();
		     relevance.setKey(key);
		     relevance.setFirstid(entry.getKey());
		     
		     relevance.setSecondid(entry.getValue());
		     relevance.setOperatetime(new Date());
		     _relevanceMapper.insert(relevance);
		}   
	}

	public List<Integer> FindUserRoleIds(Integer firstId) {
		// TODO Auto-generated method stub
		return _relevanceMapper.FindUserRoleIds(firstId);
	}


	public List<Integer> FindModuleIds(Integer firstId, List<Integer> ids) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("firstId", firstId);
        params.put("ids", ids);
		return _relevanceMapper.FindModuleIds(params);
		
	}

	public List<Integer> FindElementIds(Integer firstId, List<Integer> ids) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("firstId", firstId);
        params.put("ids", ids);
		return _relevanceMapper.FindElementIds(params);
	}

	public List<Integer> FindResourceIds(Integer firstId, List<Integer> ids) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("firstId", firstId);
        params.put("ids", ids);
		return _relevanceMapper.FindResourceIds(params);
	}

	public List<Integer> FindOrgids(Integer firstId, List<Integer> ids) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("firstId", firstId);
        params.put("ids", ids);
		return _relevanceMapper.FindOrgIds(params);
	}

}
