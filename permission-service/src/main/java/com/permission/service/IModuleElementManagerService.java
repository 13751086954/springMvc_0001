package com.permission.service;

import java.util.List;

import com.permission.model.vo.ModuleElementVM;
import com.permission.pojo.ModuleElementWithBLOBs;

public interface IModuleElementManagerService {

	public void AddOrUpdate(ModuleElementWithBLOBs model);

	public List<ModuleElementWithBLOBs> LoadByModuleId(int id);

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
	public List<ModuleElementVM> LoadWithAccess(String accessType, int firstId, int moduleId);

	public void Delete(int id);

	public void AssignForRole(int roleId,int moduleId, List<Integer> menuIds);
	
	public void AssignForUser(int userId, int moduleId, List<Integer> ids);

}
