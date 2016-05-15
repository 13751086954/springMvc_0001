package com.permission.common.helper;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WebSessionImpl implements ISessionHelper {

	private final String SessionUser = "SESSION_USER";
	 
	@Autowired  
	private HttpSession session;  
	   	 
    public <T> void AddSessionUser(T user){
    	 session.setAttribute(SessionUser, user);
     }
         
	@SuppressWarnings("unchecked")
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

     public  void Clear(){
    	 session.removeAttribute(SessionUser);
     }
}
