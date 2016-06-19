package com.permission.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.permission.common.utils.SessionService;
import com.permission.model.BjuiResponse;
import com.permission.model.bo.ModuleBO;
import com.permission.model.vo.LoginUserVM;
import com.permission.model.vo.ModuleView;
import com.permission.pojo.Module;
import com.permission.service.IModuleManagerService;

/**
 * 模块元素管理，无需权限控制
 * @author milanyangbo
 *
 */
@Controller
@RequestMapping("/modulemanager")
public class ModuleManagerController {

	@Resource
	IModuleManagerService _moduleManagerService;

	@Resource
	SessionService _sessionHelper;

	BjuiResponse bjuiResponse=new BjuiResponse();

	@RequestMapping(value="/index.do")
	public String Index(){
		return "modulemanager/index";
	}

	/**
	 * 用于选择模块时使用
	 * @param userId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/lookupmultiforuser.do")
	public String LookUpMultiForUser(int userid,ModelMap model){
		model.addAttribute("userid", userid);
		return "modulemanager/lookUpMultiForUser";
	}

	/**
	 * 为角色分配模块
	 * @param roleId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/lookupmultiforrole.do")
	public String LookUpMultiForRole(int roleid,ModelMap model){
		model.addAttribute("roleid", roleid);
		return "modulemanager/lookUpMultiForRole";
	}


	/**
	 * 加载模块下面的所有模块
	 * @param orgId
	 * @param pageCurrent
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/load.do")
	public ModuleBO Load(Integer orgid , Integer pageCurrent , Integer pageSize ) {
		if (pageCurrent == null) {
			pageCurrent = 1;
		}
		if (pageSize == null) {
			pageSize = 30;
		}
		return _moduleManagerService.Load(orgid, pageCurrent, pageSize);
	}

	/**
	 * 直接加载所有的模块
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/loadfortree.do")
	public List<ModuleView> LoadForTree() throws Exception{
		List<ModuleView> orgs = ((LoginUserVM)_sessionHelper.GetSessionUser()).getModules();
		//添加根节点
		//orgs.Add(new Module
		//{
		//    Id = 0,
		//    ParentId = -1,
		//    Name = "根节点",
		//    CascadeId = "0"
		//});
		return orgs;
	}
	/**
	 * 直接加载所有的模块
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/loadmodulewithroot.do")
	public List<ModuleView> LoadModuleWithRoot() throws Exception{ 
		List<ModuleView> orgs = new ArrayList<ModuleView>();
		for (ModuleView moduleView : ((LoginUserVM)_sessionHelper.GetSessionUser()).getModules()) {
			orgs.add(moduleView);
		};
		ModuleView module = new ModuleView();
		module.setId(0);
		module.setParentid(-1);
		module.setName("根节点");
		module.setCascadeid("0");
		//添加根节点			
		orgs.add(module);				
		return orgs;
	}

	@ResponseBody
	@RequestMapping(value="/loadforuser.do")
	public List<Module> LoadForUser(int userid){
		List<Module> orgs = _moduleManagerService.LoadForUser(userid);
		//添加根节点
		Module module = new Module();
		module.setId(0);
		module.setParentid(-1);
		module.setName("用户及角色拥有的模块");
		module.setCascadeid("0");
		orgs.add(module);
		return orgs;
	}

	@ResponseBody
	@RequestMapping(value="/loadforrole.do")
	public List<Module> LoadForRole(int roleid){
		List<Module> orgs = _moduleManagerService.LoadForRole(roleid);
		//添加根节点
		Module module = new Module();
		module.setId(0);
		module.setParentid(-1);
		module.setName("已为角色分配的模块");
		module.setCascadeid("0");
		orgs.add(module);
		return orgs;
	}

	@ResponseBody
	@RequestMapping(value="/assignmoduleforrole.do")
	public BjuiResponse AssignModuleForRole(int roleid, String moduleIds){		
		try{
			List<Integer> ids =new ArrayList<Integer>();        	 
			String[] strs = moduleIds.split("|");
			for (String str : strs) {
				ids.add(Integer.getInteger(str));
			}         
			_moduleManagerService.AssignModuleForRole(roleid, (Integer[])ids.toArray());
		}
		catch (Exception e) {
			bjuiResponse.setMessage(e.getMessage());
			bjuiResponse.setStatusCode("300");
		}
		return bjuiResponse;
	}

	@ResponseBody
	@RequestMapping(value="/assignmoduleforuser.do")
	public BjuiResponse AssignModuleForUser(int userid, String moduleIds){		
		try{
			List<Integer> ids =new ArrayList<Integer>();        	 
			String[] strs = moduleIds.split("|");
			for (String str : strs) {
				ids.add(Integer.getInteger(str));
			}         
			_moduleManagerService.AssignModuleForUser(userid, (Integer[])ids.toArray());
		}
		catch (Exception e) {
			bjuiResponse.setMessage(e.getMessage());
			bjuiResponse.setStatusCode("300");
		}
		return bjuiResponse;
	}

	@RequestMapping(value="/add.do")
	public String Add(Integer id,ModelMap model) {
		if (id == null) {
			id = 0;
		}
		Module module= _moduleManagerService.Find(id);
		model.addAttribute("model",module);
		return "modulemanager/add";
	}

	@ResponseBody
	@RequestMapping(value="/add.do",method = RequestMethod.POST)
	public BjuiResponse Add(Module model) {
		try{
			_moduleManagerService.AddOrUpdate(model);
		}
		catch (Exception e) {
			bjuiResponse.setMessage(e.getMessage());
			bjuiResponse.setStatusCode("300");
		}
		return bjuiResponse;
	}

	@ResponseBody
	@RequestMapping(value="/delete.do")
	public BjuiResponse Delete(String id) {
		try{
			String[] strs = id.split("|");
			for (String str : strs) {
				_moduleManagerService.Delete(Integer.getInteger(str));
			}        
		}
		catch (Exception e) {
			bjuiResponse.setMessage(e.getMessage());
			bjuiResponse.setStatusCode("300");
		}
		return bjuiResponse;
	}
}
