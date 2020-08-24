package com.naver.oauth.repo;

import com.naver.oauth.dto.User;


public interface UserRepo {
	public User login(String id);
	public boolean signup(User user);
}
