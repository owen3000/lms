package com.cafe24.lms.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.dto.JSONResult;
import com.cafe24.lms.service.UserService;

@Controller("apiUserController")
@RequestMapping(value= {"/api/user"})
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@RequestMapping("/checkEmail")
	@ResponseBody
	public JSONResult chekcId(
			@RequestParam(value="email",required=true,defaultValue="")String email) {
		System.out.println("email:"+email);
		
		if( "".equals(email) ) {
			return JSONResult.success("none");
		}
		
		if( userService.findEmail(email) != null ) {
			return JSONResult.success("exist");
		}
		return JSONResult.success("none");
	}
}
