package com.cafe24.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafe24.lms.domain.Item;


public interface ItemRepository extends JpaRepository<Item, Long> {

	List<Item> findById(Long no);
	
}
