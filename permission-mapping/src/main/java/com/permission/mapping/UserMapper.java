package com.permission.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.permission.common.orm.PageInfo;
import com.permission.pojo.User;


public interface UserMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	List<User> LoadUserListPage(@Param("page")PageInfo page) ;

	List<User> LoadInOrgListPage(@Param("page")PageInfo page,@Param("orgId")List<Integer> orgId) ;

	User FindSingle(String account) ;
	
	Integer GetUserCntInOrg(List<Integer> getSubOrgIds);

}