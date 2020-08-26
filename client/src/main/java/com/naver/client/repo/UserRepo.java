package com.naver.client.repo;

import com.naver.client.dto.User;

public interface UserRepo {
	public User login(String id);
	public boolean signup(User user);
	public boolean update(User user);
}
