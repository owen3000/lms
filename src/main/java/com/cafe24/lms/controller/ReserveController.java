package com.cafe24.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.interceptor.AuthUser;
import com.cafe24.lms.domain.User;
import com.cafe24.lms.service.ReserveService;

@Controller
public class ReserveController {

	@Autowired
	private ReserveService reserveService;
	
	@RequestMapping( "/reserve" )
	public String reserve( @AuthUser User authUser,
			@RequestParam(name="item-id",required=true,defaultValue="")String itemId) {
		
		System.out.println(authUser);
		System.out.println(itemId);
		boolean rentResult = false;
		rentResult = reserveService.reserve(authUser, itemId);
		System.out.println("rentResult:"+rentResult);
		
		return "redirect:/reserved";
	}
}
