/**
 * 
 */
package com.permission.service;

import com.permission.model.viewmodel.LoginUserVM;

/**
 * @author milanyangbo
 *
 */
public interface ILoginService {

	public LoginUserVM Login(String userName, String password) throws Exception;
	
	public void CheckPassword(String sqlpassword, String password) throws Exception;
	
	public LoginUserVM LoginByDev();
}
