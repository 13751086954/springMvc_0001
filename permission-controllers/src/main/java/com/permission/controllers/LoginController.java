package com.permission.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

	/**
	 * Spring 不但支持自己定义的@Autowired注解，还支持几个由JSR-250规范定义的注解，它们分别是@Resource、@PostConstruct以及@PreDestroy
	 * 。
	 * 
	 * 　　@Resource的作用相当于@Autowired，只不过@Autowired按byType自动注入，而@Resource默认按
	 * byName自动注入罢了
	 * 。@Resource有两个属性是比较重要的，分别是name和type，Spring将@Resource注解的name属性解析为bean的名字，
	 * 而type属性则解析为bean的类型
	 * 。所以如果使用name属性，则使用byName的自动注入策略，而使用type属性时则使用byType自动注入策略
	 * 。如果既不指定name也不指定type属性，这时将通过反射机制使用byName自动注入策略。
	 * 
	 * 　　@Resource装配顺序
	 * 
	 * 　　1. 如果同时指定了name和type，则从Spring上下文中找到唯一匹配的bean进行装配，找不到则抛出异常
	 * 
	 * 　　2. 如果指定了name，则从上下文中查找名称（id）匹配的bean进行装配，找不到则抛出异常
	 * 
	 * 　　3. 如果指定了type，则从上下文中找到类型匹配的唯一bean进行装配，找不到或者找到多个，都会抛出异常
	 * 
	 * 　　4.
	 * 如果既没有指定name，又没有指定type，则自动按照byName方式进行装配（见2）；如果没有匹配，则回退为一个原始类型（UserDao
	 * ）进行匹配，如果匹配则自动装配；
	 * 
	 * 　　1.6. @PostConstruct（JSR-250）
	 * 
	 * 在方法上加上注解@PostConstruct，这个方法就会在Bean初始化之后被Spring容器执行（注：Bean初始化包括，实例化Bean，
	 * 并装配Bean的属性（依赖注入））。
	 */
	@Autowired 
	ILoginService _loginService;

	@Autowired 
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
