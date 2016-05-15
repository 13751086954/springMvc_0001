package com.permission.controllers;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.permission.common.helper.ISessionHelper;
import com.permission.model.BjuiResponse;
import com.permission.model.viewmodel.LoginUserVM;
import com.permission.service.ILoginService;



@Controller
@RequestMapping("/login")
public class LoginController {
  
	 @Resource
	 ILoginService _loginService;
	
	 @Resource
	 ISessionHelper _sessionHelper;
  
	
	 @RequestMapping(value = "/index.do",method = RequestMethod.GET)
	 public String index(){		 
		return "login/index";
     }
	 
	 @RequestMapping(value = "/login.do",method = RequestMethod.POST)
	 public String login(String username, String password,ModelMap model) throws Exception{			 
		 try {
			 LoginUserVM  loginUser=_loginService.Login(username,password);
			 _sessionHelper.AddSessionUser(loginUser);			
			return "redirect:/index/home";
			
		} catch (Exception e) { 
			
			BjuiResponse response=new BjuiResponse();
			response.setStatusCode("300");
			response.setMessage(e.getMessage());
			model.addAttribute("response", response);
			
			return "login/index";
		}
     }
	 
	  /**
	   * 开发者登陆
	   * @return
	   */
	 @RequestMapping(value = "/loginByDev.do")
     public String loginByDev(ModelMap model){
    	 
         try{
        	 LoginUserVM  loginUser=_loginService.LoginByDev();
        	 _sessionHelper.AddSessionUser(loginUser);
        	 return "redirect:/index/home";

         }
         catch (Exception e){
        	 
        	 BjuiResponse response=new BjuiResponse();
 			 response.setStatusCode("300");
 			 response.setMessage(e.getMessage());
 			 model.addAttribute("response", response);
 			 
             return "login/index";
         }
     }
     
	 @RequestMapping(value = "/logout.do")
     public String logout(){
		 _sessionHelper.Clear();
    	 return "login/index";
     }
	
}
