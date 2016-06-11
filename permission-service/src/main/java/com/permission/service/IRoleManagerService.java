package com.permission.service;

import java.util.List;

import com.permission.model.bo.RoleBO;
import com.permission.model.vo.RoleVM;
import com.permission.pojo.Role;

public interface IRoleManagerService {

	public Integer GetRoleCntInOrg(Integer orgId);

	/**
	 * 加载一个部门及子部门全部Roles
	 * @param orgId
	 * @param pageindex
	 * @param pagesize
	 * @return
	 */
	RoleBO Load(Integer orgId, Integer pageindex, Integer pagesize);

	/**
	 * 获取当前组织的所有下级组织
	 * @param orgId
	 * @return
	 */
	public List<Integer> GetSubOrgIds(Integer orgId);

	public Role Find(Integer id);

	public void Delete(Integer id);

	public void AddOrUpdate(Role role);

	public List<RoleVM> LoadForOrgAndUser(Integer orgId, Integer userId);

	public void AccessRole(Integer userId, List<Integer> roleIds);


}
