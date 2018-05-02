package com.cafe24.lms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// @Auth(value=Auth.Role.ADMIN)
@Controller
@RequestMapping( "/admin" )
public class AdminController {
	
	@RequestMapping( { "", "/rent", "/main" } )
	public String main( Model model ) {
		return "admin/rent";
	}
	
	@RequestMapping( "/reserve" )
	public String board() {
		return "admin/reserve";
	}
	
}
