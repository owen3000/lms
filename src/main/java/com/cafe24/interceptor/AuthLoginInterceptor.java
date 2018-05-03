package com.cafe24.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.lms.domain.User;
import com.cafe24.lms.service.UserService;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String email =request.getParameter("email");
		String password =request.getParameter("password");

		WebApplicationContext wac =
		WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		UserService userService =
				wac.getBean(UserService.class);
		
		User vo = new User();
		vo.setEmail(email);
		vo.setPassword(password);
		User authUser = userService.login( vo );
		if( authUser == null ) {
			System.out.println("[AuthLoginInterceptor:preHandle] / if(authUser==null)");
			response.sendRedirect(request.getContextPath()+"/user/loginForm?result=fail");
			return false;
		}
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		response.sendRedirect(request.getContextPath()); 

		return false;
	}
	
}
