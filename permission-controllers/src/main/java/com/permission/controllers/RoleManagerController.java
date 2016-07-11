package com.permission.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.permission.model.BjuiResponse;
import com.permission.model.bo.RoleBO;
import com.permission.model.vo.RoleVM;
import com.permission.pojo.Role;
import com.permission.service.IRoleManagerService;

@Controller
@RequestMapping("/rolemanager")
public class RoleManagerController {

	@Autowired 
	IRoleManagerService _roleManagerService;

	BjuiResponse _bjuiResponse= new BjuiResponse();	

	@RequestMapping(value="/index.do")
	public String Index(){
		return "rolemanager/index";
	}

	@RequestMapping(value="/add.do")
	public String Add(Integer id,ModelMap model) {
		if (id == null) {
			id = 0;
		}
		Role role= _roleManagerService.Find(id);
		model.addAttribute("model",role);
		return "rolemanager/add";
	}

	/**
	 * 添加或修改角色
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add.do",method = RequestMethod.POST)
	public BjuiResponse Add(Role model){
		try{
			_roleManagerService.AddOrUpdate(model);
		}
		catch (Exception e) {
			_bjuiResponse.setMessage(e.getMessage());
			_bjuiResponse.setStatusCode("300");
		}
		return _bjuiResponse;
	}

	/**
	 * 加载角色下面的所有用户
	 * @param categoryId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/load.do")
	public RoleBO Load(Integer orgid,  Integer pageCurrent , Integer pageSize ) {
		if (pageCurrent == null) {
			pageCurrent = 1;
		}
		if (pageSize == null) {
			pageSize = 30;
		}
		return _roleManagerService.Load(orgid, pageCurrent, pageSize);
	}

	@ResponseBody
	@RequestMapping(value="/delete.do")
	public BjuiResponse Delete(int id) {
		try {
			_roleManagerService.Delete(id);
		}
		catch (Exception e) {
			_bjuiResponse.setMessage(e.getMessage());
			_bjuiResponse.setStatusCode("300");
		}
		return _bjuiResponse;
	}

	@RequestMapping(value="/lookupmulti.do")
	public String LookupMulti(int userid,ModelMap model){
		model.addAttribute("userid", userid);
		return "rolemanager/lookupMulti";
	}

	@ResponseBody
	@RequestMapping(value="/loadfororganduser.do")
	public List<RoleVM> LoadForOrgAndUser(int orgid, int userid){
		return _roleManagerService.LoadForOrgAndUser(orgid, userid);
	}

	@ResponseBody
	@RequestMapping(value="/accessroles.do")
	public BjuiResponse AccessRoles(int userid, String ids) {
		try{
			List<Integer>  roleids=new ArrayList<Integer>();        	 
			String[] strs = ids.split("|");
			for (String str : strs) {
				roleids.add(Integer.getInteger(str));
			}         
			_roleManagerService.AccessRole(userid, roleids);
		}
		catch (Exception e) {
			_bjuiResponse.setMessage(e.getMessage());
			_bjuiResponse.setStatusCode("300");
		}
		return _bjuiResponse;
	}
}
