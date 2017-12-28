package cn.et.less01.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.et.less01.dao.UserMapper;

@Controller
public class SpringLoginController {
	
	@Autowired
	UserMapper um;
	
	@RequestMapping("/loginShiro")
	public String Login(String userName,String password,Model model){
		
		Subject currentUser = SecurityUtils.getSubject();
		 UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		 try {
			    currentUser.login( token );
			    model.addAttribute("menuList", um.queryMenuByMenu(userName));
			    
			   //是否认证通过
			   
			   return "/save.jsp";
			   
			} catch ( UnknownAccountException uae ) {
			   System.out.println("账号错误");
			  
			} catch ( IncorrectCredentialsException ice ) {
			    System.out.println("密码不匹配");
			 
			} catch ( LockedAccountException lae ) {
				System.out.println("账号被锁定");
				
			} catch ( AuthenticationException ae ) {
			   System.out.println("未知错误");
			}
		 return "/un.html";
	}
	
	
	
}
