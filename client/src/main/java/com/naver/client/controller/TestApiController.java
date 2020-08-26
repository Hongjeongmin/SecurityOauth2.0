package com.naver.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naver.client.dto.Oauth_client_details;
import com.naver.client.outhservice.Oauth_client_detailsService;

@RequestMapping("/api")
@RestController
public class TestApiController {
	
	
	@Autowired
	Oauth_client_detailsService ocdService;
	
	@GetMapping("/test")
	public String test() {
		return "SUCCESS";
	}
	
}
