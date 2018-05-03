package com.cafe24.lms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cafe24.lms.domain.Item;
import com.cafe24.lms.domain.Rent;
import com.cafe24.lms.domain.User;

public interface RentRepository extends JpaRepository<Rent, Long> {

	Rent findOneByUserAndItem(User user, Item item);
	
	@Query("SELECT r FROM Rent r WHERE r.item = :item AND r.reserve IS NULL")
	Rent findByItemAndReserveNull(@Param("item")Item item );
	
	@Query("SELECT MAX(r.reserve.turn) FROM Rent r WHERE r.item= :item ")
	Long findReserveMaxByItem(@Param("item")Item item);
	
	@Query("SELECT r FROM Rent r WHERE r.item = :item AND r.reserve.turn = :value")
	Rent findByItemAndReserveMax (@Param("item")Item item, @Param("value")Long value);

	Page<Rent> findAllByReserveIsNull(Pageable pageable);
	
	Page<Rent> findAllByReserveIsNotNull(Pageable pageable);
}
