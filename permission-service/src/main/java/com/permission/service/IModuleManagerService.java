package com.permission.service;

import java.util.List;

import com.permission.model.bo.ModuleBO;
import com.permission.model.vo.ModuleView;
import com.permission.pojo.Module;

public interface IModuleManagerService {

	/**
	 * 加载一个节点下面的所有
	 * @param parentId
	 * @param pageindex
	 * @param pagesize
	 */
    public ModuleBO Load(Integer parentId, Integer pageindex, Integer pagesize);
    	
    /**
     * 为树型结构提供数据
     * @return
     */
    public List<Module> LoadForTree();
    
    /**
     * 以组合的方式显示所有的模块信息
     * @param parentId
     * @return
     */
    public List<ModuleView> LoadByParent(Integer parentId);
    
    public Module Find(int id);
    
    public void Delete(int id);
    
    public void AddOrUpdate(Module vm) throws Exception;
    
    /**
     * 加载特定用户的模块
     *  TODO:这里会加载用户及用户角色的所有模块，“为用户分配模块”功能会给人一种混乱的感觉，但可以接受
     * @param userId
     * @return
     */
    public List<Module> LoadForUser(Integer userId);
    
    /**
     * 为特定的用户分配模块
     * @param userId
     * @param ids
     */
    public void AssignModuleForUser(Integer userId, Integer[] ids);
    
    /**
     * 加载特定角色的模块
     * @param roleId
     * @return 
     */
    public List<Module> LoadForRole(Integer roleId);
    
    /**
     * 为特定的角色分配模块
     * @param roleId
     */
    public void AssignModuleForRole(Integer roleId, Integer[] ids);
}
