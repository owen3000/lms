package com.cafe24.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cafe24.dto.JSONResult;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class GlobalExceptionHandler {


	@ExceptionHandler( Exception.class)
	public void handlerException(HttpServletRequest request,
			HttpServletResponse response, Exception e) throws Exception {
		//기술침투 어쩔수없다고함.
		//1. 로깅
		StringWriter errors = new StringWriter();
		// errors에 씀.
		e.printStackTrace(new PrintWriter(errors));
		System.out.println(errors);

		// accept 받고
		String accept = request.getHeader("accept");
		// 브라우져마다 다르니 함부로 몇번쨰 파싱하면안되고 매칭해서 :  .*는 어느 문자나
		if(accept.matches(".*application/json.*")) {
			//실패 json 응답을 여기에
			//이짓을 메세지 컨버터가 하고있는거죠.
			// 보내야할 객체만들고
			JSONResult jsonResult = JSONResult.fail(errors.toString());
			// 객체를 스트링으로 바꿔서
			String json = new ObjectMapper().writeValueAsString(jsonResult);
			System.out.println(json);
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().println(json);
			
		}else {
			//웹 응답.
			// 2. 사과페이지
			request.getRequestDispatcher("/WEB-INF/views/error/error.jsp").
			forward(request, response);
		}

	}
}
