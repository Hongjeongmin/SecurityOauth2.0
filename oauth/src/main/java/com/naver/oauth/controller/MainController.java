package com.naver.oauth.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.naver.oauth.dto.User;
import com.naver.oauth.userservice.UserService;

@Controller
public class MainController {
	@Autowired
	UserService UserService;

	@GetMapping("/")
	public ModelAndView main(Model model, Principal principal) {
		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object object = authentication.getPrincipal();
		
		
		
		
		ModelAndView m = null;

		System.out.println("안녕하세요 ㅎㅎ");
		if (principal == null) {
			m = new ModelAndView("home");
			m.addObject("message", "인증 되지 않은 사람입니다. 인증에 제한이됩니다.");
		} else {
			m = new ModelAndView("main");
			m.addObject("message", principal.getName() + "님 안녕하세요 인증이 된사람입니다.");
		}
		return m;
	}
	
	@PostMapping("/")
	public ModelAndView loginMain(Model model, Principal principal) {
		ModelAndView m = null;

		System.out.println("안녕하세요 ㅎㅎggg");
		if (principal == null) {
			m = new ModelAndView("home");
			m.addObject("message", "인증 되지 않은 사람입니다. 인증에 제한이됩니다.");
		} else {
			m = new ModelAndView("main");
			m.addObject("message", principal.getName() + "님 안녕하세요 인증이 된사람입니다.");
		}
		return m;
	}
	
	@GetMapping("login")
	public String loginForm() {
		return "login";
	}

	@GetMapping("logout")
	public String logoutForm() {
		return "logout";
	}

	@GetMapping("/admin")
	public String adminForm() {
		return "admin";
	}

	@GetMapping("/user")
	public String userForm() {
		return "user";
	}
	
	@GetMapping("/signup")
	public String signupForm() {
		System.out.println("signup");
		return "signup";
	}
	
	@PostMapping("signup")
	public String signupProccess(HttpServletRequest request) {
		User user = new User();
		user.setId(request.getParameter("id"));
		user.setPwd(request.getParameter("pwd"));
		user.setNickname(request.getParameter("nickname"));
		UserService.signup(user);
		return "login";
	}

}
