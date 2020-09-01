package com.naver.projectserver.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.naver.projectserver.common.Pwd;
import com.naver.projectserver.mapper.Client_manager;
import com.naver.projectserver.mapper.Oauth_client_details;
import com.naver.projectserver.mapper.User;
import com.naver.projectserver.service.Client_managerService;
import com.naver.projectserver.service.Oauth_client_detailsService;
import com.naver.projectserver.service.UserService;

@Controller
public class MainController {
	@Autowired
	UserService userService;

	@Autowired
	Pwd pwd;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	Oauth_client_detailsService ocdService;

	@Autowired
	Client_managerService client_managerService;

	@GetMapping("/")
	public ModelAndView main(Model model, Principal principal) {

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
	public ModelAndView oauthlist(Principal principal) {
		ModelAndView m = new ModelAndView("oauthlist");
		List<Client_manager> clinet_managers = client_managerService.select(principal.getName());
		m.addObject("client_managers", clinet_managers);

		return m;
	}

	@GetMapping("getoauth")
	public String getoauthForm() {

		return "getoauth";
	}

	@Transactional
	@PostMapping("getoauth")
	public String getoauthProccess(@RequestParam List<String> authorized_grant_types, @RequestParam List<String> scopes,
			@RequestParam("web_server_redirect_uri") String web_server_redirect_uri,
			@RequestParam("appname") String appname, Principal principal) {
		/*
		 * input values scope,grant_type은 ','로 구분한다.
		 */

		Client_manager client_manager = new Client_manager();
		client_manager.Create_client_manger(principal.getName(), appname, pwd, web_server_redirect_uri);

		Oauth_client_details ocd = new Oauth_client_details();
		ocd.setClient_id(client_manager.getClient_id());
		ocd.setWeb_server_redirect_uri(web_server_redirect_uri);
		ocd.setClient_secret(client_manager.getClient_secret());
		ocd.setAuthorized_grant_types(String.join(",", authorized_grant_types));
		ocd.setScope(String.join(",", scopes));

		client_managerService.insert(client_manager);
		ocdService.insert(ocd);

		return "redirect:/";
	}

	@Transactional
	@GetMapping("delete")
	public ModelAndView delete(@RequestParam("client_id") String client_id, Principal principal) {
		ModelAndView m = new ModelAndView("oauthlist");
		List<Client_manager> clinet_managers = client_managerService.select(principal.getName());
		m.addObject("client_managers", clinet_managers);

		client_managerService.delete(client_id);
		ocdService.delete(client_id);
		return m;
	}

	@Transactional
	@GetMapping("update")
	public ModelAndView update(@RequestParam("client_id") String client_id, Principal principal) {
		Client_manager cm = new Client_manager();
		Oauth_client_details ocd = new Oauth_client_details();

		cm.setId(principal.getName());
		cm.setClient_secret(pwd.getRnadomcode(60));
		ocd.setClient_id(client_id);
		ocd.setClient_secret(cm.getClient_secret());
		client_managerService.updateSecret(cm);
		ocdService.update(ocd);

		ModelAndView m = new ModelAndView("oauthlist");
		List<Client_manager> clinet_managers = client_managerService.select(principal.getName());
		m.addObject("client_managers", clinet_managers);

		return m;
	}

}
