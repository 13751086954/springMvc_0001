package com.permission.common.helper;

public interface ISessionHelper {

	 public <T> void AddSessionUser(T user);
        	
	 public <T> T GetSessionUser() throws Exception  ;

     public  void Clear();
}
