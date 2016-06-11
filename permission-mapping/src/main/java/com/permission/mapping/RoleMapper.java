package com.permission.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.permission.common.orm.PageInfo;
import com.permission.pojo.Role;

public interface RoleMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Role record);

	int insertSelective(Role record);

	List<Role> LoadRoleListPage(@Param("page")PageInfo page);

	List<Role> LoadInOrgListPage(@Param("page")PageInfo page,@Param("orgIds")List<Integer> orgIds);

	Role selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Role record);

	int updateByPrimaryKey(Role record);

	List<Role> FindUserRoles(@Param("ids")List<Integer> userRoleIds);
	
	List<Role> FindByOrgId(@Param("orgId")Integer orgId);

	List<Role> FindAll();

	Integer GetRoleCntInOrgs(List<Integer> getSubOrgIds);
}