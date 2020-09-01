package com.naver.projectserver.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor@NoArgsConstructor
@Getter@Setter@Builder
public class User {
	String id;
	String pwd;
	String nickname;

	public void encodePassword(PasswordEncoder passwordEncoder) {
		this.pwd = passwordEncoder.encode(this.pwd);
	}
	
	
}
