package com.permission.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.permission.common.utils.JsonUtils;
import com.permission.common.utils.SessionService;
import com.permission.controllers.annotation.Anonymous;
import com.permission.model.BjuiResponse;
import com.permission.model.vo.LoginUserVM;
import com.permission.pojo.Org;
import com.permission.service.IOrgManagerService;

@Controller
@RequestMapping("/orgmanager")
public class OrgManagerController {

	@Resource
	IOrgManagerService _orgManagerService;

	@Resource
	SessionService _sessionHelper;

	BjuiResponse _bjuiResponse=new BjuiResponse();

	@RequestMapping(value="/index.do")
	public String Index(){
		return "orgmanager/index";
	}

	@RequestMapping(value="/lookupmultiforuser.do")
	public String LookUpMultiForUser(int userid,ModelMap model){
		model.addAttribute("userid", userid);
		return "orgmanager/lookUpMultiForUser";
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
	 * 选择上级机构页面
	 * @return
	 */
	@RequestMapping(value="/lookupparent.do")
	public String LookupParent(){
		return "orgmanager/lookupParent";
	}

	@RequestMapping(value="/addorg.do")
	public String AddOrg(){
		return "orgmanager/addOrg";
	}

	@Anonymous
	@ResponseBody
	@RequestMapping(value="/loadorg.do")
	public List<Org> LoadOrg() throws Exception {
		List<Org> orgs = new ArrayList<Org>();
		for (Org org : ((LoginUserVM)_sessionHelper.GetSessionUser()).getAccessedOrgs()) {
			orgs.add(org);
		};
		Org org2 = new Org();
		org2.setId(0);
		org2.setParentid(-1);
		org2.setName("根节点");
		org2.setCascadeid("0");
		//添加根节点			
		orgs.add(org2);				
		return orgs;
	}

	@ResponseBody
	@RequestMapping(value="/loadforuser.do")
	public List<Org> LoadForUser(int userid) {
		List<Org> orgs = _orgManagerService.LoadForUser(userid);
		Org org2 = new Org();
		org2.setId(0);
		org2.setParentid(-1);
		org2.setName("用户及角色可访问的部门");
		org2.setCascadeid("0");
		orgs.add(org2);				
		return orgs;
	}

	@ResponseBody
	@RequestMapping(value="/loadforrole.do")
	public List<Org> LoadForRole(int roleid){
		List<Org> orgs = _orgManagerService.LoadForRole(roleid);
		Org org2 = new Org();
		org2.setId(0);
		org2.setParentid(-1);
		org2.setName("已为角色分配的可访问部门");
		org2.setCascadeid("0");
		orgs.add(org2);				
		return orgs;
	}

	@ResponseBody
	@RequestMapping(value="/assignorgforrole.do")
	public BjuiResponse AssignOrgForRole(int roleid, String moduleIds){
		try{
			List<Integer> ids =new ArrayList<Integer>();        	 
			String[] strs = moduleIds.split("|");
			for (String str : strs) {
				ids.add(Integer.getInteger(str));
			}         
			_orgManagerService.AssignModuleForRole(roleid,ids);
		}
		catch (Exception e){
			_bjuiResponse.setMessage(e.getMessage());
			_bjuiResponse.setStatusCode("300"); 
		}

		return _bjuiResponse;
	}

	@ResponseBody
	@RequestMapping(value="/assignorgforuser.do")
	public BjuiResponse AssignOrgForUser(int userid, String moduleIds) {
		try{
			List<Integer> ids =new ArrayList<Integer>();        	 
			String[] strs = moduleIds.split("|");
			for (String str : strs) {
				ids.add(Integer.getInteger(str));
			}         
			_orgManagerService.AssignModuleForUser(userid, ids);
		}
		catch (Exception e){
			_bjuiResponse.setMessage(e.getMessage());
			_bjuiResponse.setStatusCode("300"); 
		}

		return _bjuiResponse;
	}

	@ResponseBody
	@RequestMapping(value="/addorg.do",method=RequestMethod.POST)
	public BjuiResponse AddOrg(Org org) {
		try {
			_orgManagerService.AddOrUpdate(org);
		}
		catch (Exception e){
			_bjuiResponse.setMessage(e.getMessage());
			_bjuiResponse.setStatusCode("300"); 
		}

		return _bjuiResponse;
	}

	@ResponseBody
	@RequestMapping(value="/editorg.do")
	public BjuiResponse EditOrg(String json) {
		try {
			Org org = JsonUtils.jsonToPojo(json, Org.class);
			_orgManagerService.AddOrUpdate(org);
		}
		catch (Exception e){
			_bjuiResponse.setMessage(e.getMessage());
			_bjuiResponse.setStatusCode("300"); 
		}

		return _bjuiResponse;
	}

	@ResponseBody
	@RequestMapping(value="/loadchildren.do")
	public List<Org> LoadChildren(int id) throws Exception {
		return _orgManagerService.LoadAllChildren(id);
	}

	@ResponseBody
	@RequestMapping(value="/delorg.do")
	public BjuiResponse DelOrg(int id) {
		try{
			_orgManagerService.DelOrg(id);
		}
		catch (Exception e){
			_bjuiResponse.setMessage(e.getMessage());
			_bjuiResponse.setStatusCode("300"); 
		}

		return _bjuiResponse;
	}
}
