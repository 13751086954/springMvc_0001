package com.permission.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.permission.model.BjuiResponse;
import com.permission.model.bo.CategoryBO;
import com.permission.pojo.Category;
import com.permission.service.ICategoryManagerService;

@Controller
@RequestMapping("/categorymanager")
public class CategoryManagerController {

	@Autowired  
	ICategoryManagerService _categoryManagerService;

	BjuiResponse _bjuiResponse= new BjuiResponse();		

	@RequestMapping(value="/index.do")
	public String Index(){
		return "categorymanager/index";
	}

	/**
	 * 加载组织下面的所有用户
	 * @param orgid
	 * @param pageCurrent
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/load.do")
	public CategoryBO Load(Integer orgid , Integer pageCurrent , Integer pageSize){
		if (pageCurrent == null) {
			pageCurrent = 1;
		}
		if (pageSize == null) {
			pageSize = 30;
		}
		return _categoryManagerService.Load(orgid, pageCurrent, pageSize);
	}

	@ResponseBody
	@RequestMapping(value="/loadfortree.do")
	public List<Category> LoadForTree() {
		List<Category> models = _categoryManagerService.LoadAll();
		Category category=  new Category();
		category.setId(0);
		category.setParentid(-1);
		category.setName("根结点");
		category.setCascadeid("0");
		//添加根节点
		models.add(category);
		return models;
	}

	@RequestMapping(value="/add.do")
	public String Add(Integer id,ModelMap model) {
		if (id == null) {
			id = 0;
		}
		Category module= _categoryManagerService.Find(id);
		model.addAttribute("model",module);
		return "modulemanager/add";
	}

	@ResponseBody
	@RequestMapping(value="/add.do",method = RequestMethod.POST)
	public BjuiResponse Add(Category model) {
		try{
			_categoryManagerService.AddOrUpdate(model);
		}
		catch (Exception e) {
			_bjuiResponse.setMessage(e.getMessage());
			_bjuiResponse.setStatusCode("300");
		}
		return _bjuiResponse;
	}

	@ResponseBody
	@RequestMapping(value="/delete.do")
	public BjuiResponse Delete(Integer id) {
		try{
			_categoryManagerService.Delete(id);
		}
		catch (Exception e) {
			_bjuiResponse.setMessage(e.getMessage());
			_bjuiResponse.setStatusCode("300");
		}
		return _bjuiResponse;
	}
}
