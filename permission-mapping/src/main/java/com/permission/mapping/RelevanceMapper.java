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
	
	void deleteByKeyAndFirstIdAndSecondIds(@Param("key")String key,@Param("firstId")Integer firstId, @Param("ids")List<Integer> secondIds);

	List<Integer> FindUserRoleIds(Integer firstId); 

	List<Integer> FindRoleIds(Integer firstId); 
	
	List<Integer> FindSecondIds(@Param("firstId")Integer firstId, @Param("key1")String key1, @Param("key2")String key2, @Param("ids")List<Integer> ids);
	
	Relevance FindAccesseType(@Param("accessType")String accessType, @Param("firstId")Integer firstId,@Param("secondId")Integer secondId) ;
}