package com.permission.shiro.service;


import java.util.HashSet;
import java.util.Set;

import com.permission.model.vo.LoginUserVM;
import com.permission.model.vo.ModuleView;
import com.permission.pojo.Role;
import com.permission.pojo.User;
import com.permission.service.ILoginService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;









import javax.annotation.Resource;

public class CustomRealm extends AuthorizingRealm {

	@Resource
	ILoginService loginService;

    // 设置realm的名称
    @Override
    public void setName(String name) {
        super.setName("customRealm");
    }

    //realm的认证方法，从数据库查询用户信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {

        // token是用户输入的用户名和密码
        // 第一步从token中取出用户名
        String account = (String) token.getPrincipal();
        
        if (account.equals("System")) {
        	User user=new User();
    		user.setAccount("System");
    		user.setName("开发者账号");
    		user.setPassword("");
    		//盐
            //String salt = user.getSalt();
            
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                    user, "",this.getName());
            return simpleAuthenticationInfo;
        }
        else{
        	// 第二步：根据用户输入的userCode从数据库查询
            User user = null;
            try {
                user = loginService.FindSingle(account);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            if(user==null){
                return null;
            }

            // 从数据库查询到密码
            String password = user.getPassword();

            //盐
            //String salt = user.getSalt();
                       
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
            		user, password,this.getName());
            return simpleAuthenticationInfo;
        }
   
    }



    // 用于授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        //从 principals获取主身份信息
        //将getPrimaryPrincipal方法返回值转为真实身份类型（在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型），
    	User activeUser =  (User) principals.getPrimaryPrincipal();
    	
        LoginUserVM loginUserVM=null;
        try {
        	 if (activeUser.getAccount().equals("System")) {
        		 loginUserVM = loginService.LoginByDev();
        	  }
        	  else {
        		  loginUserVM= loginService.Login(activeUser.getAccount(), activeUser.getPassword());
			  }			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            
        //角色名的集合
        Set<String> roles = new HashSet<String>();
        //权限名的集合
        Set<String> permissions = new HashSet<String>();
        
        for (Role role : loginUserVM.getRoles()) {
        	roles.add(role.getName());
		}
        
        for (ModuleView moduleView : loginUserVM.getModules()) {
        	permissions.add(moduleView.getName());
		}
    
        //查到权限数据，返回授权信息(要包括 上边的permissions)
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //将上边查询到授权信息填充到simpleAuthorizationInfo对象中      
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    //清除缓存
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }


}
