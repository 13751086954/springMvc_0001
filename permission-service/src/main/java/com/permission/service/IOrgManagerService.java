package com.permission.service;

import java.util.List;

import com.permission.pojo.Org;

public interface IOrgManagerService {

	public List<Org> GetAll();

	/**
	 * 部门的直接子部门
	 *<para>TODO:可以根据用户的喜好决定选择LoadAllChildren或LoadDirectChildren</para>
	 * @param orgId
	 * The org unique identifier.
	 * @return
	 */
	public List<Org> LoadDirectChildren(Integer orgId);

	/**
	 * 得到部门的所有子部门
	 * <para>如果orgId为0，表示取得所有部门</para>
	 * @param orgId
	 * @return
	 * @throws Exception 
	 */
	public List<Org> LoadAllChildren(Integer orgId) throws Exception;

	public Integer AddOrUpdate(Org org) throws Exception;

	/**
	 * 删除指定ID的部门及其所有子部门
	 * @param id
	 */
	public void DelOrg(Integer id);

	/**
	 * 加载特定用户的角色
	 * TODO:这里会加载用户及用户角色的所有角色，“为用户分配角色”功能会给人一种混乱的感觉，但可以接受
	 * @param userId
	 * @return
	 */
	public List<Org> LoadForUser(Integer userId);

	/**
	 * 为特定的用户分配角色
	 * @param userId
	 * @param ids
	 */
	public void AssignModuleForUser(Integer userId, List<Integer> ids);

	/**
	 * 加载特定角色的角色
	 * @param roleId
	 * @return
	 */
	public List<Org> LoadForRole(Integer roleId);

	/**
	 * 为特定的角色分配角色
	 * @param roleId
	 * @param ids
	 */
	public void AssignModuleForRole(Integer roleId, List<Integer> ids);
}
