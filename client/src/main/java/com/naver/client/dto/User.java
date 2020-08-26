package com.naver.client.dto;

import org.springframework.security.crypto.password.PasswordEncoder;

public class User {
	String id;
	String pwd;
	String nickname;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void encodePassword(PasswordEncoder passwordEncoder) {
		this.pwd = passwordEncoder.encode(this.pwd);
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", pwd=" + pwd + ", nickname=" + nickname + "]";
	}
	
}
