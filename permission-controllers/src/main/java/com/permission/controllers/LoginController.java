package com.permission.controllers;

import javax.annotation.Resource;
/*
 * 
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
 */
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.permission.common.utils.SessionService;
import com.permission.model.BjuiResponse;
import com.permission.model.vo.LoginUserVM;
import com.permission.service.ILoginService;;



@Controller
@RequestMapping("/login")
public class LoginController {

	@Resource
	ILoginService _loginService;

	@Resource
	SessionService _sessionHelper;

	@RequestMapping(value = "/index.do",method = RequestMethod.GET)
	public String index(){		 
		return "login/index";
	}
	/*
	 * 
	 @RequestMapping(value = "/login.do",method = RequestMethod.POST)
	 public String login(String username, String password,boolean rememberMe,ModelMap model) throws Exception{		

		    Subject currentUser = SecurityUtils.getSubject();
	        if (!currentUser.isAuthenticated()) {
	            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
	            token.setRememberMe(rememberMe);
	            try{
	                currentUser.login(token);
	            }catch(UnknownAccountException ex){
	            	BjuiResponse response=new BjuiResponse();
	    			response.setStatusCode("300");
	    			response.setMessage("账号不存在！");
	    			model.addAttribute("response", response);			
	    			return "login/index";
	            }catch(IncorrectCredentialsException ex){
	            	BjuiResponse response=new BjuiResponse();
	    			response.setStatusCode("300");
	    			response.setMessage("密码错误！");
	    			model.addAttribute("response", response);			
	    			return "login/index";
	            }catch(LockedAccountException ex){
	            	BjuiResponse response=new BjuiResponse();
	    			response.setStatusCode("300");
	    			response.setMessage("账号已被锁定，请与系统管理员联系！");
	    			model.addAttribute("response", response);			
	    			return "login/index";
	            }catch(AuthenticationException ex){
	            	BjuiResponse response=new BjuiResponse();
	    			response.setStatusCode("300");
	    			response.setMessage("您没有授权！");
	    			model.addAttribute("response", response);			
	    			return "login/index";
	            }
	        }
	        Session  session= currentUser.getSession();
	        session.setAttribute("currentUser", _loginService.Login(username, password));
	        return "redirect:/home/index.do";
	 }
	 */

	@RequestMapping(value = "/login.do",method = RequestMethod.POST)
	public String login(String username, String password,ModelMap model) throws Exception{			 
		try {
			LoginUserVM  loginUser=_loginService.Login(username,password);
			_sessionHelper.AddSessionUser(loginUser);			
			return "redirect:/home/index.do";

		} catch (Exception e) { 			
			BjuiResponse response=new BjuiResponse();
			response.setStatusCode("300");
			response.setMessage(e.getMessage());
			model.addAttribute("response", response);			
			return "login/index";
		}
	}

	/*
	 * 
		 @RequestMapping(value = "/loginByDev.do")
	     public String loginByDev(boolean rememberMe,ModelMap model){	    	 
			    Subject currentUser = SecurityUtils.getSubject();
		        if (!currentUser.isAuthenticated()) {
		            UsernamePasswordToken token = new UsernamePasswordToken("System", "");
		            token.setRememberMe(rememberMe);
		            currentUser.login(token);
		        }		        
		        Session  session= currentUser.getSession();
		        session.setAttribute("currentUser", _loginService.LoginByDev());
		        return "redirect:/home/index.do";
		 }
	 */

	/**
	 * 开发者登陆
	 * @return
	 */
	@RequestMapping(value = "/loginByDev.do")
	public String loginByDev(ModelMap model){

		try{
			LoginUserVM  loginUser=_loginService.LoginByDev();
			_sessionHelper.AddSessionUser(loginUser);
			return "redirect:/home/index.do";

		}
		catch (Exception e){       	 
			BjuiResponse response=new BjuiResponse();
			response.setStatusCode("300");
			response.setMessage(e.getMessage());
			model.addAttribute("response", response);			 
			return "login/index";
		}
	}

	/*
	 *
	 @RequestMapping(value = "/logout.do")
	 public String logout(){
    	 return "login/index";
     }
	 */
	
	@RequestMapping(value = "/logout.do")
	public String logout(){
		_sessionHelper.Clear();
		return "login/index";
	}


}
