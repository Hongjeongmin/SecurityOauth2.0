package com.naver.client.repo;

import java.util.List;

import com.naver.client.dto.User;

public interface UserRepo {
	public User login(String id);
	public boolean signup(User user);
	public boolean update(User user);
	public List<User> selectAll();
}
