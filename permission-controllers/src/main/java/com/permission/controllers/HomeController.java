package com.permission.controllers;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.permission.model.viewmodel.LoginUserVM;

@Controller
@RequestMapping("/home")
public class HomeController {

	 @RequestMapping(value = "/index.do")
	 public String index(ModelMap model){	
		Subject currentUser = SecurityUtils.getSubject();
		Session  session= currentUser.getSession();
		LoginUserVM  loginUserVM = (LoginUserVM)session.getAttribute("currentUser");
		model.addAttribute("modules", loginUserVM.getModules());
		return "home/index";
     }
}
