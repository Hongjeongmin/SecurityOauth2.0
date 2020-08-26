package com.naver.client.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.naver.client.Pwd;
import com.naver.client.dto.User;
import com.naver.client.userservice.UserService;

@Controller
public class MainController {
	@Autowired
	UserService userService;
	@Autowired
	Pwd pwd;
	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping("/")
	public ModelAndView main(Model model, Principal principal) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object object = authentication.getPrincipal();

		ModelAndView m = null;

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

		if (principal == null) {
			m = new ModelAndView("home");
			m.addObject("message", "인증 되지 않은 사람입니다. 인증에 제한이됩니다.");
		} else {
			m = new ModelAndView("main");
			m.addObject("message", principal.getName() + "님 안녕하세요");
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
		return "signup";
	}

	@PostMapping("signup")
	public String signupProccess(HttpServletRequest request) {
		User user = new User();
		user.setId(request.getParameter("id"));
		user.setPwd(request.getParameter("pwd"));
		user.setNickname(request.getParameter("nickname"));
		userService.signup(user);
		return "login";
	}

	@GetMapping("forgot")
	public String forgotForm() {
		return "forgot";
	}

	@PostMapping("forgot")
	public ModelAndView forgotProccess(HttpServletRequest request) {

		String id = request.getParameter("id");
		ModelAndView m = new ModelAndView("checkpass");

		if (userService.login(id) != null) {
			String newPass = pwd.getRnadomcode();
			User user = new User();
			user.setId(id);
			user.setPwd(newPass);
			if (userService.update(user)) {
				m.addObject("message", "비밀 번호 찾기 성공 : " + newPass);
			} else {
				m.addObject("message", "비밀번호 변경과정에서 실패 했습니다.");
			}

		} else {
			m.addObject("message", "비밀 번호 찾기 실패 아이디를 확인해 주세요");
		}
		return m;
	}

	@GetMapping("oauthlist")
	public String oauthlist() {
		return "oauthlist";
	}

	@GetMapping("getoauth")
	public String getoauthForm() {
		return "getoauth";
	}

	@PostMapping("getoauth")
	public String getoauthProccess(HttpServletRequest request) {
		return "redirect:/";
	}
	
	@GetMapping("test")
	public String testForm() {
		return "test";
	}

}
