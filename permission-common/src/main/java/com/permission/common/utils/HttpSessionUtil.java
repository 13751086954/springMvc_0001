package com.permission.common.utils;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HttpSessionUtil implements SessionService {

	private final String SessionUser = "SESSION_USER";
	 
	@Autowired  
	private HttpSession session;  
	
	@Override   	 
    public <T> void AddSessionUser(T user){
    	 session.setAttribute(SessionUser, user);
     }
         
	@SuppressWarnings("unchecked")
	@Override
	public  <T> T GetSessionUser() throws Exception  { 
    	 try 
         { 
    	    return (T) session.getAttribute(SessionUser);
         }
         catch (Exception e)
         {
             throw new Exception(e.getMessage());
         }
	} 
	
	@Override
    public  void Clear(){
    	session.removeAttribute(SessionUser);
    }
	
}
