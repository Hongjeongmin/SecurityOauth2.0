package com.naver.oauthREST.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.naver.oauthREST.dto.User;
import com.naver.oauthREST.repo.UserRepo;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired 
	PasswordEncoder passwordEncoder;

	@Override
	public User login(String id) {
		return userRepo.login(id);
	}

	@Override
	public boolean signup(User user) {
		user.encodePassword(passwordEncoder);
		return userRepo.signup(user);
	}

}
