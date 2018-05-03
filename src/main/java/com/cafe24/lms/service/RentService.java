package com.cafe24.lms.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.lms.domain.Item;
import com.cafe24.lms.domain.Rent;
import com.cafe24.lms.domain.User;
import com.cafe24.lms.repository.ItemRepository;
import com.cafe24.lms.repository.RentRepository;
import com.cafe24.util.WebUtil;

@Service
@Transactional
public class RentService {

	@Autowired
	private RentRepository rentRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	public boolean rent(User authUser, String itemId) {
		
		Long lItemId =
				WebUtil.checkParameter(itemId, -1L);
		if( lItemId == -1L ) {
			System.out.println("[RentService:rent] if( lItemId == -1L ) ");
			return false;
		}
		Item item = itemRepository.findOne( lItemId );
		// 해당 Item이 있는지 확인
		if( item == null ) {
			System.out.println("[RentService:rent] if( item == null ) ");
			return false;
		}
		// 해당 Item이 이미 대여중인지 확인.
		List<Item> list = itemRepository.findByItem(item);
		System.out.println("[RentService:rent]:"+list);
		if( !(list.size() == 0 || list == null) ) {
			System.out.println("[RentService:rent] 이미 대여중인 책입니다.");
			return false;
		}
		
		// 대여
		Date nowDate = new Date();
		Calendar temp=Calendar.getInstance ( );
		temp.add ( Calendar.DAY_OF_MONTH, 7 );
		
		Rent rentData = new Rent();
		rentData.setRentDate( nowDate );
		rentData.setReturnDate( temp.getTime() );
		rentData.setUser( authUser );
		rentData.setItem( item );
		rentData.setReserve( null );
		rentRepository.save( rentData );
		
		// 대여여부 수정
		item.setRented("yes");
		
		return true;
	}

}
