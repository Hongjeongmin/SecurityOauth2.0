package com.naver.client.userservice;

import java.util.List;

import com.naver.client.dto.User;

public interface UserService {
	User login(String id);
	boolean signup(User user);
	public boolean update(User user);
	public List<User> selectAll();
}
