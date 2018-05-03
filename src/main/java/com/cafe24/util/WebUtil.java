package com.cafe24.util;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class WebUtil {
	
	
	public static Long checkParameter(String value,Long defaultValue) {

		if("".equals(value)) {
			return defaultValue;
		}
		
		boolean flag = false; 
		if(value!=null)
			flag = java.util.regex.Pattern.matches("^[0-9]*$", value);
		
		if(!flag) {
			return defaultValue;
		}
		return Long.parseLong(value);
	}
	
	public static Integer checkParameter(String value,Integer defaultValue) {

		if("".equals(value)) {
			return defaultValue;
		}
		
		boolean flag = false; 
		if(value!=null)
			flag = java.util.regex.Pattern.matches("^[0-9]*$", value);
		
		if(!flag) {
			return defaultValue;
		}
		return Integer.parseInt(value);
	}

	public static void redirect(HttpServletRequest request, HttpServletResponse response, String url) 
			throws ServletException, IOException {
		response.sendRedirect(url);
	}
	
	public static void forword(HttpServletRequest request, HttpServletResponse response, String path) 
			throws ServletException, IOException {
		RequestDispatcher rd=
				request.getRequestDispatcher(path);
		//forwrd
		rd.forward(request, response);
	}
	
	public static boolean checkBindingResult(BindingResult result) {
		boolean flag = false;
		if(result.hasErrors()) {
			//에러출력
			List<ObjectError> list = result.getAllErrors();
			/*for(ObjectError e: list)
				System.out.println("ObjectError: "+e);*/
			flag = true;
		}
		return flag;
	}
	
	public static <T> boolean checkListEmptyOrNull(List<T> list, String message) {
		if( list == null  ) {
			System.out.println( message );
			return true;
		}
		if( list.isEmpty() ) {
			System.out.println( message );
			return true;
		}
		return false;
	}
	
	public static Calendar dateToCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
		return calendar;
	}
	
	//Convert Calendar to Date
	private static Date calendarToDate(Calendar calendar) {
		return calendar.getTime();
	}

}
