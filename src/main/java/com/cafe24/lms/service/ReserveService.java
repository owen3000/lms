package com.cafe24.lms.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.lms.domain.Item;
import com.cafe24.lms.domain.Rent;
import com.cafe24.lms.domain.Reserve;
import com.cafe24.lms.domain.User;
import com.cafe24.lms.repository.ItemRepository;
import com.cafe24.lms.repository.RentRepository;
import com.cafe24.util.WebUtil;

@Service
@Transactional
public class ReserveService {

	@Autowired
	private RentRepository rentRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	public boolean reserve(User authUser, String itemId) {
		
		Long lItemId =
				WebUtil.checkParameter(itemId, -1L);
		if( lItemId == -1L ) {
			System.out.println("[ReserveService:reserve] if( lItemId == -1L ) ");
			return false;
		}
		//해당 Item이 있는지 확인
		Item item = itemRepository.findOne( lItemId );
		if( item == null ) {
			System.out.println("[ReserveService:reserve] if( item == null ) ");
			return false;
		}
		// 대여 중인지 확인.
		if( rentRepository.findByItemAndReserveNull(item) == null ) {
			System.out.println("[ReserveService:reserve] 대여중인 책이 아님. ");
			return false;
		}
		// 같은 회원이 같은 Item 대여하려 하는지 체크
		if( rentRepository.findOneByUserAndItem(authUser,item) != null) {
			System.out.println("[ReserveService:reserve] 같은 회원이 예약 불가! ");
			return false;
		}
		
		// 해당 Item의 예약 순번 MAX값 가져오기
		Long reserveMax = rentRepository.findReserveMaxByItem(item);
		// 해당 Item의 예약이 없는 상태
		if( reserveMax == null) {
			Rent rented = rentRepository.findByItemAndReserveNull(item);
			
			Date rentDate = rented.getRentDate();
			Calendar calendar = WebUtil.dateToCalendar(rentDate);
			// 반납일 7일 더하기
			calendar.add ( Calendar.DAY_OF_MONTH, 7 );
			
			// 예약 저장
			Rent rentData = new Rent();
			rentData.setRentDate( rentDate ); // 대출일
			rentData.setReturnDate( calendar.getTime() ); //반납일
			rentData.setUser( authUser );
			rentData.setItem( item );
			Reserve reserve = new Reserve();
			reserve.setTurn(1L);
			rentData.setReserve( reserve );
			rentRepository.save( rentData );
		}
		else { // 예약 추가 하기 
			Rent rented = rentRepository.findByItemAndReserveMax( item, reserveMax );
			
			Date rentDate = rented.getRentDate();
			Calendar calendar = WebUtil.dateToCalendar(rentDate);
			// 반납일 7일 더하기
			calendar.add ( Calendar.DAY_OF_MONTH, 7 );
			
			// 예약 저장
			Rent rentData = new Rent();
			rentData.setRentDate( rentDate ); // 대출일
			rentData.setReturnDate( calendar.getTime() ); //반납일
			rentData.setUser( authUser );
			rentData.setItem( item );
			Reserve reserve = new Reserve();
			reserve.setTurn( reserveMax + 1L );
			rentData.setReserve( reserve );
			rentRepository.save( rentData );
		}
		
		return true;
	}
	


}
