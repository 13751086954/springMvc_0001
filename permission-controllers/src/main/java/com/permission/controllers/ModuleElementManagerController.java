package com.permission.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.permission.model.BjuiResponse;
import com.permission.model.vo.ModuleElementVM;
import com.permission.pojo.ModuleElementWithBLOBs;
import com.permission.service.IModuleElementManagerService;

@Controller
@RequestMapping("/moduleelementmanager")
public class ModuleElementManagerController {

	@Resource
	IModuleElementManagerService _moduleElementManagerService;

	@RequestMapping(value="/index.do")
	public String Index(int id,ModelMap model) {
		model.addAttribute("moduleId", id);
		model.addAttribute("modules", _moduleElementManagerService.LoadByModuleId(id));
		return "moduleelementmanager/index";
	}

	@RequestMapping(value="/addoreditbutton.do")
	public BjuiResponse AddOrEditButton(ModuleElementWithBLOBs button){		
		BjuiResponse _bjuiResponse= new BjuiResponse();				
		try {
			_moduleElementManagerService.AddOrUpdate(button);
		} catch (Exception e) {
			// TODO: handle exception
			_bjuiResponse.setStatusCode("300");
			_bjuiResponse.setMessage(e.getMessage());
		}
		return _bjuiResponse;
	}

	@RequestMapping(value="/delbutton.do")
	public BjuiResponse DelButton(int id) {
		BjuiResponse _bjuiResponse= new BjuiResponse();				
		try{
			_moduleElementManagerService.Delete(id);
		}
		catch (Exception e){
			_bjuiResponse.setStatusCode("300");
			_bjuiResponse.setMessage(e.getMessage());
		}
		return _bjuiResponse;
	}

	@RequestMapping(value="/assignforrole.do")
	public String AssignForRole(int roleId,ModelMap model){
		model.addAttribute("roleId", roleId);
		return "moduleelementmanager/assignForRole";
	}

	/**
	 * 为角色分配菜单
	 * @param roleId
	 * 角色ID
	 * @param moduleId
	 * 模块ID
	 * @param menuIds
	 * 菜单ID列表
	 * @return
	 */
	@RequestMapping(value="/assignforrole.do",method= RequestMethod.POST)
	public BjuiResponse AssignForRole(int roleId,int moduleId, String menuIds){
		BjuiResponse _bjuiResponse= new BjuiResponse();			
		try{
			String[] strs=  menuIds.split(",");
			List<Integer> ids =new ArrayList<Integer>();
			for (String str : strs) {
				ids.add(Integer.getInteger(str)) ;
			}
			_moduleElementManagerService.AssignForRole(roleId,moduleId, ids);
		}
		catch (Exception e){
			_bjuiResponse.setStatusCode("300");
			_bjuiResponse.setMessage(e.getMessage());
		}
		return _bjuiResponse;
	}

	@RequestMapping(value="/loadforrole.do")
	public List<ModuleElementVM> LoadForRole(int roleId, int orgId){
		return	_moduleElementManagerService.LoadWithAccess("RoleElement", roleId, orgId);
	}

	@RequestMapping(value="/assignforuser.do")
	public String AssignForUser(int userId,ModelMap model) {
		model.addAttribute("userId", userId);
		return "moduleelementmanager/assignForUser";
	}

	/**
	 * 为用户分配菜单
	 * @param userId
	 * 用户ID
	 * @param moduleId
	 * 模块ID
	 * @param menuIds
	 * 菜单ID列表
	 * @return
	 */
	@RequestMapping(value="/assignforuser.do",method= RequestMethod.POST)
	public BjuiResponse AssignForUser(int userId,int moduleId, String menuIds){
		BjuiResponse _bjuiResponse= new BjuiResponse();
		try{
			String[] strs=  menuIds.split(",");
			List<Integer> ids =new ArrayList<Integer>();
			for (String str : strs) {
				ids.add(Integer.getInteger(str)) ;
			}
			_moduleElementManagerService.AssignForUser(userId,moduleId, ids);
		}
		catch (Exception e) {
			_bjuiResponse.setStatusCode("300");
			_bjuiResponse.setMessage(e.getMessage());
		}
		return _bjuiResponse;
	}

	@RequestMapping(value="/loadforuser.do")
	public List<ModuleElementVM> LoadForUser(int roleId, int orgId){
		return	_moduleElementManagerService.LoadWithAccess("UserElement", roleId, orgId);
	}
}
