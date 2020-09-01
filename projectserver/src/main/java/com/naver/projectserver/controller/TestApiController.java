package com.naver.projectserver.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naver.projectserver.service.Oauth_client_detailsService;
import com.naver.projectserver.service.UserService;

@RequestMapping("/api")
@RestController
public class TestApiController {

	@Autowired
	Oauth_client_detailsService ocdService;

	@Autowired
	UserService userService;

	@GetMapping("/read")
	public String testread(Principal principal) {
		return "read : " + principal.getName();
	}

	@GetMapping("/write")
	public String testwrite(Principal principal) {
		return "write : " + principal.getName();
	}

}
