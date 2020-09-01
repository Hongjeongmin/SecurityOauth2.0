package com.naver.client.userservice;

import com.naver.client.entity.User;

public interface UserService {
	User login(String id);
	boolean signup(User user);
	public boolean update(User user);
	public User select(String id);
	public boolean updateNickname(User user);
}
