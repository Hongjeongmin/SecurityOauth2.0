package com.naver.oauthREST.oauthservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.naver.oauthREST.userservice.UserService;

@Service
public class OauthService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.naver.oauthREST.dto.User user = userService.login(username);
		
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		//admin 아이디만 권한을 준다.
		String role = "admin".equals(user.getId()) ? "ADMIN" : "USER";
		return User.builder()
				.username(user.getId())
				.password(user.getPwd())
				.roles(role)
				.build();
	}

}
