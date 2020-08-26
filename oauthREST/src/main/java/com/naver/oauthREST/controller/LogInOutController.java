package com.naver.oauthREST.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogInOutController {

	/*
	 * 
	 * 
	 */

//	@GetMapping("loign")
//	public String loginForm() {
//		System.out.println("login");
//		return "login";
//	}
//	
//	@GetMapping("logout")
//	public String logoutForm(){
//		System.out.println("logout");
//		return "logout";
//	}
	
	@GetMapping("/swagger/test")
	public String swagger() {
		System.out.println("-------swager------------");
		return "swagger";
	}
}
