package com.cafe24.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.lms.domain.Item;
import com.cafe24.lms.service.ItemService;
import com.cafe24.util.PagingBean;
import com.cafe24.util.WebUtil;

@Controller
public class MainController {
	
	@Autowired
	private ItemService itemService;

	@RequestMapping( { "", "/main" } )
	public String index( Model model,
			@RequestParam(name="page",required=true,defaultValue="1")String page) {
		
		Integer iPage = WebUtil.checkParameter(page, 1);

		Page<Item> list = itemService.getList(iPage,3);
		PagingBean pb = new PagingBean(list.getTotalElements(),
				list.getNumber()+1, list.getSize(), 3);
		
		model.addAttribute("list", list);
		model.addAttribute("pb", pb);
		return "main/index";
	}
	

	@RequestMapping( "/rented" )
	public String rented( Model model ) {
		return "main/rented";
	}
	
	@RequestMapping( "/reserved" )
	public String reserved( Model model ) {
		return "main/reserved";
	}
	
}
