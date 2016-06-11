package com.permission.service;

import java.util.List;

import com.permission.model.vo.ModuleElementVM;
import com.permission.pojo.ModuleElementWithBLOBs;

public interface IModuleElementManagerService {

	public void AddOrUpdate(ModuleElementWithBLOBs model);

	public List<ModuleElementWithBLOBs> LoadByModuleId(Integer id);

	/**
	 * 获取带有授权状态的菜单列表
	 * @param accessType
	 * 授权类型，当前有RoleElement/UserElement
	 * @param firstId 
	 * 当为RoleElement时，表示RoleId
	 * 当为UserElement时，表示UserId
	 * @param moduleId
	 * 模块ID
	 * @return
	 */
	public List<ModuleElementVM> LoadWithAccess(String accessType, Integer firstId, Integer moduleId);

	public void Delete(int id);

	public void AssignForRole(Integer roleId,Integer moduleId, List<Integer> menuIds);
	
	public void AssignForUser(Integer userId, Integer moduleId, List<Integer> ids);
}
