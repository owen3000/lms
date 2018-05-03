package com.cafe24.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.cafe24.lms.domain.User;

public class AuthUserHandlerMethodArgumentResolver 
						implements HandlerMethodArgumentResolver{
	@Override
	public Object resolveArgument(MethodParameter param,
			ModelAndViewContainer arg1, NativeWebRequest nwr,
			WebDataBinderFactory arg3) throws Exception {

		// 해석이 필요한 놈인지 supportsParameter 메서드로 확인.
		if( !(supportsParameter(param))) {
			//현재 Resolver가 처리할 파라미터가 아닌 경우 UNRESOLVED를 리턴한다.
			return WebArgumentResolver.UNRESOLVED;
		}
		HttpServletRequest request =
					nwr.getNativeRequest(HttpServletRequest.class);
		HttpSession session =
					request.getSession();
		if( session == null ) {

			return null;
		}
		
		return session.getAttribute("authUser");
	}

	@Override
	public boolean supportsParameter(MethodParameter param) {
		// 1. @Blog 붙어있는지
		AuthUser authUser = 
				param.getParameterAnnotation(AuthUser.class);
		if(authUser == null) {
			return false;
		}
		// 2. 붙어있는 상태. @Blog 붙어있는 놈의 타입 확인
		if( !(param.getParameterType().equals(User.class)) ) {
			return false;
		}
		
		return true;
	}
	
	

}
