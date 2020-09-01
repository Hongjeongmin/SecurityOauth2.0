package com.naver.client.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.naver.client.entity.User;
import com.naver.client.repo.UserRepo;


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

	@Override
	public boolean update(User user) {
		user.encodePassword(passwordEncoder);
		return userRepo.update(user);
	}

	@Override
	public User select(String id) {
		return userRepo.select(id);
	}

	@Override
	public boolean updateNickname(User user) {
		return userRepo.updateNickname(user);
	}

}
