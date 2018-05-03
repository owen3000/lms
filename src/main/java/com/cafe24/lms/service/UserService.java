package com.cafe24.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.lms.domain.User;
import com.cafe24.lms.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public void join ( User user ) {
		
		user.setRole("user");
		userRepository.save( user );
	}
	
	public User login( User user ) {
		return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
	}

	public void modify(User user) {
		user.setRole("user");
		userRepository.save( user );
	}
	
	public User findEmail( String email ) {
		return userRepository.findByEmail( email );
	}
}
