package com.naver.client.userservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.client.dto.User;
import com.naver.client.repo.UserRepo;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepo userRepo;

	@Override
	public User login(String id) {
		return userRepo.login(id);
	}

	@Override
	public boolean signup(User user) {
		return userRepo.signup(user);
	}

	@Override
	public boolean update(User user) {
		return userRepo.update(user);
	}

	@Override
	public List<User> selectAll() {
		return userRepo.selectAll();
	}

}
