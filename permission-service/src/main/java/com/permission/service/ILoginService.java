/**
 * 
 */
package com.permission.service;

import com.permission.model.viewmodel.LoginUserVM;
import com.permission.pojo.User;

/**
 * @author milanyangbo
 *
 */
public interface ILoginService {

	public LoginUserVM Login(String username, String password) throws Exception;
	
	public void CheckPassword(String sqlpassword, String password) throws Exception;
	
	public LoginUserVM LoginByDev();
	
	public User FindSingle(String account);
}
