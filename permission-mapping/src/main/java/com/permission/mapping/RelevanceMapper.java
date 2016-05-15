package com.permission.mapping;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.permission.pojo.Relevance;

public interface RelevanceMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Relevance record);

    int insertSelective(Relevance record);

    Relevance selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Relevance record);

    int updateByPrimaryKey(Relevance record);
    
    void deleteByKeyAndFirstIds(Map<String, Object> params);
    
    List<Integer> FindUserRoleIds(@Param("firstId")Integer firstId);
    
    List<Integer> FindModuleIds(Map<String, Object> params);
    
    List<Integer> FindElementIds(Map<String, Object> params);
    
    List<Integer> FindResourceIds(Map<String, Object> params);
    
    List<Integer> FindOrgIds(Map<String, Object> params);
    
    
}