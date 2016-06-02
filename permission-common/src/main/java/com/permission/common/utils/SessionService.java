package com.permission.common.utils;

public interface SessionService {

	 public <T> void AddSessionUser(T user);
        	
	 public <T> T GetSessionUser() throws Exception  ;

     public  void Clear();
}
