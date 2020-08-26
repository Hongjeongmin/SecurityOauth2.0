package com.naver.oauthREST.repo;

import org.springframework.stereotype.Repository;

import com.naver.oauthREST.dto.User;

@Repository
public interface UserRepo {
	public User login(String id);
	public boolean signup(User user);
}
