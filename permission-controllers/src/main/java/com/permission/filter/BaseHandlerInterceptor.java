package com.permission.filter;

import java.lang.reflect.Method;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.permission.common.utils.SessionService;
import com.permission.model.vo.LoginUserVM;
import com.permission.model.vo.ModuleView;



public class BaseHandlerInterceptor extends HandlerInterceptorAdapter {

	@Resource
	SessionService _sessionHelper;
	 
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception { 		
		LoginUserVM  loginUserVM = (LoginUserVM)_sessionHelper.GetSessionUser();
		if(loginUserVM == null){
			response.sendRedirect("login/index.do");
			//request.getRequestDispatcher("/WEB-INF/Views/login/index.jsp").forward(request, response);		
			return false;
		}
		
		String uri = request.getRequestURI(); //获取的URI如：/user/getuser
		String controllername = uri.substring(1,uri.lastIndexOf("/")); 		
        String actionname = uri.substring(uri.lastIndexOf("/")+1);   
        
		String className = handler.getClass().getName();
        className = className.substring(0,className.indexOf("$"));//获取的类名如：pakageName.className$xxx$xx
        
        Class<?> clazz = Class.forName(className);
		Method method= clazz.getMethod(actionname);
		 if (method == null)
             throw new Exception("未能找到Action");
		 List<ModuleView> moduleViews= loginUserVM.getModules();
		 ModuleView module = null;
		 for (ModuleView moduleView : moduleViews) {
			 if (moduleView.getUrl().contains(controllername)) {
				 module=moduleView;
				 break;
			}
		 }
		 Boolean isAnnotation= method.isAnnotationPresent(Anonymous.class);
		 if (module == null && isAnnotation == false){
			 response.sendRedirect("login/index.do");
         }	   
	    return true;    
	} 
	
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)throws Exception {    
    	LoginUserVM  loginUserVM = (LoginUserVM)_sessionHelper.GetSessionUser();
    	String uri = request.getRequestURI(); //获取的URI如：/user/getuser
		String controllername = uri.substring(1,uri.lastIndexOf("/")); 		
    	List<ModuleView> moduleViews= loginUserVM.getModules();    	
    	ModuleView module = null;
		for (ModuleView moduleView : moduleViews) {
			 if (moduleView.getUrl().contains(controllername)) {
				 module=moduleView;
				 break;
			}
		 }
        loginUserVM.getModules();
    	modelAndView.addObject("module",module);
    }    
    
    public void afterCompletion( HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {    
    }  
}
