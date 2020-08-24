package com.naver.oauth.userservice;

import com.naver.oauth.dto.User;

public interface UserService {
	User login(String id);
	boolean signup(User user);
}
