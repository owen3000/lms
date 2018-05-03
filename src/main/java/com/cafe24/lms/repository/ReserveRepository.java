package com.cafe24.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafe24.lms.domain.Reserve;

public interface ReserveRepository extends JpaRepository<Reserve, Long> {

}
