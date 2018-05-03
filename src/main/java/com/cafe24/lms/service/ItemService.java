package com.cafe24.lms.service;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.lms.domain.Item;
import com.cafe24.lms.repository.ItemRepository;

@Service
@Transactional
public class ItemService {

	@Resource
	private ItemRepository itemRepository;
	
	public Page<Item> getList(int page, int size){
		
		PageRequest pageRequest = 
			new PageRequest(page-1, size, new Sort(Direction.DESC,"id"));
		return itemRepository.findAll(pageRequest);
	}
}
