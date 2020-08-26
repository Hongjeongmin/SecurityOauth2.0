package com.naver.oauthREST.userservice;

import com.naver.oauthREST.dto.User;

public interface UserService {
	User login(String id);
	boolean signup(User user);
}
