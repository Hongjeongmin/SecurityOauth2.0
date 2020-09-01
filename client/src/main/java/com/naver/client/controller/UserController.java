package com.naver.client.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naver.client.entity.User;
import com.naver.client.mapper.UserDto;
import com.naver.client.userservice.UserService;
@RequestMapping("/api")
@RestController
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/users")
	public ResponseEntity searchUser(Principal principal) {

		User user = userService.select(principal.getName());
		Map<String, String> map = new HashMap<>();
		map.put("id", user.getId());
		map.put("nickname", user.getNickname());
		return ResponseEntity.ok(map);
	}

	@PutMapping("/users")
	public ResponseEntity updateUser(Principal principal, @RequestBody UserDto userDto) {
		User user = userService.login(principal.getName());
		
		
		user.setNickname(userDto.getNicknamel());		
		System.out.println(user);
		if(userService.updateNickname(user)) {
			
			Map map = new HashMap<>();
			return ResponseEntity.ok("success");
		}
		
		return ResponseEntity.ok("bad");
		
	}
	
	
}
