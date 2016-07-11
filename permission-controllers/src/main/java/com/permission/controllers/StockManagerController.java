package com.permission.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.permission.model.BjuiResponse;
import com.permission.model.bo.StockBO;
import com.permission.pojo.Stock;
import com.permission.service.IStockManagerService;

@Controller
@RequestMapping("/stockmanager")
public class StockManagerController {

	@Autowired 
	IStockManagerService _stockManagerService;

	BjuiResponse _bjuiResponse= new BjuiResponse();
	
	@RequestMapping(value="/index.do")
	public String Index(){
		return "stockmanager/index";
	}

	@RequestMapping(value="/add.do")
	public String Add(Integer id,ModelMap model) {
		if (id == null) {
			id = 0;
		}
		Stock stock= _stockManagerService.Find(id);
		model.addAttribute("model",stock);
		return "stockmanager/add";
	}

	/**
	 * 添加或修改Stock
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add.do",method = RequestMethod.POST)
	public BjuiResponse Add(Stock model){
		try{
			_stockManagerService.AddOrUpdate(model);
		}
		catch (Exception e) {
			_bjuiResponse.setMessage(e.getMessage());
			_bjuiResponse.setStatusCode("300");
		}
		return _bjuiResponse;
	}

	/**
	 * 加载节点下面的所有Stocks
	 * @param categoryId
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value="/load.do")
	public StockBO Load(Integer parentid,  Integer pageCurrent , Integer pageSize ) throws Exception {
		if (pageCurrent == null) {
			pageCurrent = 1;
		}
		if (pageSize == null) {
			pageSize = 30;
		}
		return _stockManagerService.Load(parentid, pageCurrent, pageSize);
	}

	@ResponseBody
	@RequestMapping(value="/delete.do")
	public BjuiResponse Delete(int id) {
		try {
			_stockManagerService.Delete(id);
		}
		catch (Exception e) {
			_bjuiResponse.setMessage(e.getMessage());
			_bjuiResponse.setStatusCode("300");
		}
		return _bjuiResponse;
	}
}
