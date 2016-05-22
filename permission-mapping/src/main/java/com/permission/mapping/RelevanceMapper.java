package com.permission.mapping;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.permission.pojo.Relevance;

public interface RelevanceMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Relevance record);

    int insertSelective(Relevance record);

    Relevance selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Relevance record);

    int updateByPrimaryKey(Relevance record);
    
    void deleteByKeyAndFirstIds(@Param("key")String key, @Param("ids")List<Integer> firstIds);
    
    List<Integer> FindUserRoleIds(Integer firstId); 

	List<Integer> FindModuleIds(@Param("firstId")Integer firstId, @Param("ids")List<Integer> ids) ;

	List<Integer> FindElementIds(@Param("firstId")Integer firstId, @Param("ids")List<Integer> ids) ;

	List<Integer> FindResourceIds(@Param("firstId")Integer firstId, @Param("ids")List<Integer> ids) ;

	List<Integer> FindOrgids(@Param("firstId")Integer firstId, @Param("ids")List<Integer> ids) ;
       
}