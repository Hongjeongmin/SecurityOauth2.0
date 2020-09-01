package com.naver.client.dto;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class UserResource extends ResourceSupport{
	
	@JsonUnwrapped
	private List<User> users;

	public UserResource(List<User> users) {
		this.users = users;
	}

	public List<User> getUsers() {
		return users;
	}

}
