package com.permission.service;

import java.util.List;

import com.permission.model.bo.RoleBO;
import com.permission.model.vo.RoleVM;
import com.permission.pojo.Role;

public interface IRoleManagerService {

	public int GetRoleCntInOrg(int orgId);

	/**
	 * 加载一个部门及子部门全部Roles
	 * @param orgId
	 * @param pageindex
	 * @param pagesize
	 * @return
	 */
	RoleBO Load(int orgId, int pageindex, int pagesize);

	/**
	 * 获取当前组织的所有下级组织
	 * @param orgId
	 * @return
	 */
	public List<Integer> GetSubOrgIds(int orgId);

	public Role Find(int id);

	public void Delete(int id);

	public void AddOrUpdate(Role role);

	public List<RoleVM> LoadForOrgAndUser(int orgId, int userId);

	public void AccessRole(int userId, List<Integer> roleIds);


}
