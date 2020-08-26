package com.naver.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class TestApiController {
	
	
	
	@GetMapping("/test")
	public String test() {
		return "SUCCESS s";
	}
}
