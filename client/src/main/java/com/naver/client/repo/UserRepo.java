package com.naver.client.repo;

import com.naver.client.entity.User;

public interface UserRepo {
	public User login(String id);
	public User select(String id);
	public boolean signup(User user);
	public boolean update(User user);
	public boolean updateNickname(User user);
}
