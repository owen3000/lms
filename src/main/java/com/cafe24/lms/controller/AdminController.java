package com.cafe24.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.interceptor.Auth;
import com.cafe24.interceptor.Auth.Role;
import com.cafe24.lms.domain.Rent;
import com.cafe24.lms.service.RentService;
import com.cafe24.lms.service.ReserveService;
import com.cafe24.util.PagingBean;
import com.cafe24.util.WebUtil;

@Auth(role=Role.ADMIN)
@Controller
@RequestMapping( "/admin" )
public class AdminController {
	
	@Autowired
	private RentService rentService;
	
	@Autowired
	private ReserveService reserveService;
	
	@RequestMapping( { "", "/rent", "/main" } )
	public String main( Model model,
			@RequestParam(name="page",required=true,defaultValue="1")String page) {
		
		Integer iPage = WebUtil.checkParameter(page, 1);
		
		Page<Rent> list = rentService.findByReserveIsNull( iPage, 3);
		PagingBean pb = new PagingBean(list.getTotalElements(),
				list.getNumber()+1, list.getSize(), 3);
		
		model.addAttribute("navigation", "rent");
		model.addAttribute("list", list);
		model.addAttribute("pb", pb);
		return "admin/rent";
	}
	
	@RequestMapping( "/reserve" )
	public String reserve( Model model,
			@RequestParam(name="page",required=true,defaultValue="1")String page  ) {
		
		Integer iPage = WebUtil.checkParameter(page, 1);
		
		Page<Rent> list = reserveService.findByReserveIsNotNull( iPage, 3);
		PagingBean pb = new PagingBean(list.getTotalElements(),
				list.getNumber()+1, list.getSize(), 3);
		
		model.addAttribute("list", list);
		model.addAttribute("pb", pb);
		return "admin/reserve";
	}
	
}
