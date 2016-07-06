package com.permission.controllers;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.permission.model.BjuiResponse;
import com.permission.model.bo.UserBO;
import com.permission.model.vo.UserView;
import com.permission.service.IUserManagerService;

@Controller
@RequestMapping("/usermanager")
public class UserManagerController {

	@Resource
	IUserManagerService _userManagerService;

	BjuiResponse _bjuiResponse= new BjuiResponse();

	@RequestMapping(value="/index.do")
	public String Index(){
		return "usermanager/index";
	}

	@RequestMapping(value="/add.do")
	public String Add(Integer id,ModelMap model) {
		if (id == null) {
			id = 0;
		}
		UserView userView= _userManagerService.Find(id);
		model.addAttribute("model",userView);
		return "usermanager/add";
	}

	/**
	 * 添加或修改
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add.do",method = RequestMethod.POST)
	public BjuiResponse Add(UserView model){
		try{
			_userManagerService.AddOrUpdate(model);
		}
		catch (Exception e) {
			_bjuiResponse.setMessage(e.getMessage());
			_bjuiResponse.setStatusCode("300");
		}
		return _bjuiResponse;
	}

	/**
	 * 加载组织下面的所有用户
	 * @param categoryId
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value="/load.do")
	public UserBO Load(Integer orgid,  Integer pageCurrent , Integer pageSize ) throws Exception {
		if (pageCurrent == null) {
			pageCurrent = 1;
		}
		if (pageSize == null) {
			pageSize = 30;
		}
		return _userManagerService.Load(orgid, pageCurrent, pageSize);
	}

	@ResponseBody
	@RequestMapping(value="/delete.do")
	public BjuiResponse Delete(int id) {
		try {
			_userManagerService.Delete(id);
		}
		catch (Exception e) {
			_bjuiResponse.setMessage(e.getMessage());
			_bjuiResponse.setStatusCode("300");
		}
		return _bjuiResponse;
	}
}
