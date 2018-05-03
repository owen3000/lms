package com.cafe24.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.lms.domain.User;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	private static final Log LOG = LogFactory.getLog(AuthInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler)
			throws Exception {
		// servlet xml에 맵핑 된대로 /** 모든 요청에 대해 들옴(exclude제외)
		//1. default handler 제외
		if( (handler instanceof HandlerMethod) == false) {
			LOG.warn("AuthInterceptor:preHandle / if( (handler instanceof HandlerMethod) == false)");
			return true; //디폴트 라는 얘기죠
		}
		
		// 디폴트 컨트롤러 뺴고 나머지 컨트롤러로 들옴
		//2. 어노테이션 정보 추출
		HandlerMethod hm = (HandlerMethod) handler;
		if (handler instanceof HandlerMethod) {
	
		
		Auth authClass = hm.getBeanType().getAnnotation(Auth.class);
		Auth authMethod = hm.getMethodAnnotation(Auth.class);
		String role = null;
		if( authClass == null ) {
			if( authMethod == null ) {
				// @Auth 가 없는 클래스 or 메서드 이므로 그냥 통과 시켜줌!!
				return true; //통과
			} else {
				System.out.println("authMethod: "+authMethod.toString());
	            role = authMethod.toString();
			}
		} else { // Class @Auth 달린경우
			// @Auth 의 role 에서 ADMIN or USER 부분만 추출
			//System.out.println("authClass: "+authClass.toString());
	        role = authClass.toString();
		}
		
		//3. 어노테이션이 있는상태. 세션체크해야지
		HttpSession session = request.getSession();
		if( session == null ) {
			System.out.println("[AuthInterceptor] if( session == null ) ");
			response.sendRedirect(request.getContextPath()+"/user/member_login");
			return false;
		}
		User authUser = (User) session.getAttribute("authUser");
		if( authUser == null ) {
			response.sendRedirect(request.getContextPath()+"/user/member_login");
			return false;
		}
		
		// authUser 권한과  @Auth 의 권한이 ADMIN or USER로 같으면 통과.
		// 유저가 유저권한에 접근 하는 경우
		if( "user".equals(authUser.getRole()) && role.contains("USER") ) {
			System.out.println("[@Auth interceptor] 유저가 유저권한 접근 통과!");
			return true;
		}
		// 관리자가 관리자권한에 접근 하는 경우
		if( "admin".equals(authUser.getRole()) && role.contains("ADMIN") ) {
			System.out.println("[@Auth interceptor] 관리자가 관리자권한 접근 통과!");
			return true;
		}
		}	
		//LOG.warn("AuthInterceptor: @Auth 허가완료");
		response.sendRedirect(request.getContextPath());
		return false;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

/*		if (handler instanceof HandlerMethod) {
		//System.out.println("postHandle");
		HandlerMethod hm = (HandlerMethod) handler;
		
			// modifying 메서드 일 경우.
			if( "modifying".equals(hm.getMethod().getName()) ) {
				RequestMapping rm = hm.getMethodAnnotation(RequestMapping.class);
				RequestMethod[] requestMethod = rm.method();
				System.out.println(rm);
				String requestMethodType = "";
				if( requestMethod.length > 0 )
					requestMethodType = requestMethod[0].toString(); //POST or GET
				//POST 방식일 경우
				if( "POST".equals(requestMethodType) ) {
					HttpSession session = request.getSession();
					if( session != null) { 
						Map<String, Object> map = modelAndView.getModel();
						//이거 map인데 
						//map: {User=User [no=2, id=a1, password=1111, ... } 
						//이런식으로 User란 이름으로 옴. controller에서
						// User authUser = userService.get(vo.getNo());
						// modelAndView.addObject(authUser); 해주었음.
						User updateUser = (User) map.get("User");
						//setAttribute하면 세션 초기화 되서 로그인 끊김...
						//session.setAttribute("authUser", authUser);
						User authUser = (User) session.getAttribute("authUser");
						authUser.setPassword(updateUser.getPassword());
						authUser.setName(updateUser.getName());
						authUser.setBirth(updateUser.getBirth());
						authUser.setTel(updateUser.getTel());
						authUser.setPhone(updateUser.getPhone());
						authUser.setZipcode(updateUser.getZipcode());
						authUser.setAddress(updateUser.getAddress());
						authUser.setEmail(updateUser.getEmail());
						System.out.println("authUser 업데이트 셋팅 완료!");
					}
				}
			}	
			
		}*/
	}//postHandle
	
}
