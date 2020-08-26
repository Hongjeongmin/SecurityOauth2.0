package com.naver.client.userservice;

import com.naver.client.dto.User;

public interface UserService {
	User login(String id);
	boolean signup(User user);
	public boolean update(User user);
}
