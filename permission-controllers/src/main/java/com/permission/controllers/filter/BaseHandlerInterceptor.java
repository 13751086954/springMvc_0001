package com.permission.controllers.filter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.permission.common.utils.SessionService;
import com.permission.controllers.annotation.Anonymous;
import com.permission.model.vo.LoginUserVM;
import com.permission.model.vo.ModuleView;



public class BaseHandlerInterceptor extends HandlerInterceptorAdapter {

	@Resource
	SessionService _sessionHelper;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception { 
		String uri = request.getServletPath(); //获取的URI如：/user/getuser
		if(uri.startsWith("/login")){
			return true; 
		}
		LoginUserVM  loginUserVM = (LoginUserVM)_sessionHelper.GetSessionUser();
		if(loginUserVM == null){
			response.sendRedirect("/login/index.do");		
			return false;
		}

		String controllername = uri.substring(1,uri.lastIndexOf("/")); 	

		String actionname = uri.substring(uri.lastIndexOf("/"));   

		String className = handler.getClass().getName();
		Class<?> clazz = Class.forName(className);
		Method[] methods = clazz.getMethods();
		Method thisMethod = null;
		for (Method method : methods) {
			Boolean isAnnotation= method.isAnnotationPresent(RequestMapping.class);
			if (isAnnotation) {			
				Annotation p = method.getAnnotation(RequestMapping.class); 
				Method m = p.getClass().getDeclaredMethod("value", null);
				String[] strs = (String[])m.invoke(p, null);
				if (strs[0].equals(actionname)) {
					thisMethod = method;
					break;
				}
			}
		}

		if (thisMethod == null)
			throw new Exception("未能找到Action");

		List<ModuleView> moduleViews= loginUserVM.getModules();
		ModuleView module = null;
		for (ModuleView moduleView : moduleViews) {
			if (moduleView.getUrl().toLowerCase().contains(controllername.toLowerCase())) {
				module=moduleView;
				break;
			}
		}
		Boolean isAnnotation= thisMethod.isAnnotationPresent(Anonymous.class);
		if (module == null && isAnnotation == false){
			response.sendRedirect("/login/index.do");
		}	   
		return true;    
	} 

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)throws Exception { 
		String uri = request.getServletPath(); //获取的URI如：/user/getuser
		if(!uri.startsWith("/login")){
			LoginUserVM  loginUserVM = (LoginUserVM)_sessionHelper.GetSessionUser();		
			String controllername = uri.substring(1,uri.lastIndexOf("/")); 		
			List<ModuleView> moduleViews= loginUserVM.getModules();    	
			ModuleView module = null;
			for (ModuleView moduleView : moduleViews) {
				if (moduleView.getUrl().toLowerCase().contains(controllername.toLowerCase())) {
					module=moduleView;
					break;
				}
			}
			if (module != null) {
				modelAndView.addObject("module",module);
			}			 
		}		
	}    

	public void afterCompletion( HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {    
	}  
}
