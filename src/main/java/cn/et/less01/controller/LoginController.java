package cn.et.less01.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("userName");
		String password=request.getParameter("password");
		//获取当前的用户
		Subject currentUser = SecurityUtils.getSubject();

		 UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		 try {
			    currentUser.login( token );
			
			   //是否认证通过
			   System.out.println(currentUser.isAuthenticated());
			  request.getRequestDispatcher("/suc.jsp").forward(request, response);
			   
			} catch ( UnknownAccountException uae ) {
			   System.out.println("账号错误");
			   request.getRequestDispatcher("/login.html").forward(request, response);
			} catch ( IncorrectCredentialsException ice ) {
			    System.out.println("密码不匹配");
			    request.getRequestDispatcher("/login.html").forward(request, response);
			} catch ( LockedAccountException lae ) {
				System.out.println("账号被锁定");
				
			} catch ( AuthenticationException ae ) {
			   System.out.println("未知错误");
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
