package com.naver.oauthREST.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

	@GetMapping("/login")
	public String loginForm() {
		System.out.println("home/login");
		return "login";
	}
	
	@GetMapping("/signup")
	public String signupForm() {
		System.out.println("home/signup");
		return "signup";
	}
	
	

}
