package com.permission.controllers.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.HandlerMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.permission.queue.QueueService;


public class UnExceptionHandler implements HandlerExceptionResolver {  

	private static Logger logger = Logger.getLogger(UnExceptionHandler.class);  

	//@Autowired
	//QueueService queueService;

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,  
			Exception ex) {

		logger.error(ex.getMessage());
		//queueService.send(request.getRequestURI(), "error", ex.getMessage());

		HandlerMethod mathod = (HandlerMethod) handler;
		ResponseBody body = mathod.getMethodAnnotation(ResponseBody.class);
		//判断有没有@ResponseBody的注解没有的话调用父方法
		if (body == null) {       	     
			return new ModelAndView("error/noAccess.do");  
		}
		else {
			//避免乱码
			response.setContentType("text/html;charset=UTF-8"); 
			try {
				response.getWriter().write("{\"statusCode\":300,\"message\":\""+ ex.getMessage() +"\"}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   		    		
			return new ModelAndView();
		}  	
	}  
}  
