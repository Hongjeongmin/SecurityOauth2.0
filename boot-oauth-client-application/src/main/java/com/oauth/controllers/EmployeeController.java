package com.oauth.controllers;

import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oauth.info.OauthInfo;

@Controller
public class EmployeeController {

	@Autowired
	OauthInfo oauthInfo;

	@GetMapping
	public ModelAndView root() {
		ModelAndView m = new ModelAndView("main");
		m.addObject("oauthInfo", oauthInfo);
		return m;
	}

	@RequestMapping(value = "/getEmployees", method = RequestMethod.GET)
	public ModelAndView getEmployeeInfo() {
		return new ModelAndView("getEmployees");
	}

//	@PostMapping("searchusers")
//	public ModelAndView searchusers(@RequestParam("access_token") String access_token,@RequestParam("nickname") String nickname) throws JsonProcessingException, IOException {
//		ModelAndView m = new ModelAndView("/");
//		// create request body
//		JSONObject request = new JSONObject();
//		request.put("username", name);
//		request.put("password", password);
//		String requestJson = "{\"nickname\":\""+ nickname + "\"}";		
//		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<String> response = null;
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Authorization", "Bearer " + access_token);
//		headers.setContentType(MediaType.APPLICATION_JSON);
//
//		HttpEntity<String> entity = new HttpEntity<>(requestJson,headers);
//		String url = oauthInfo.getTarget_uri() + "/api/users";
//		response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
//		ObjectMapper mapper = new ObjectMapper();
//		JsonNode node = mapper.readTree(response.getBody());
//		
//		return m;
//	}

	@GetMapping("test")
	public ModelAndView test(@RequestParam("code") String code) throws JsonProcessingException, IOException {
		ResponseEntity<String> response = null;
		RestTemplate restTemplate = new RestTemplate();

		String credentials = oauthInfo.client_id + ":" + oauthInfo.secret;
		String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", "Basic " + encodedCredentials);

		HttpEntity<String> request = new HttpEntity<String>(headers);

		String access_token_url = oauthInfo.getTarget_uri() + "/oauth/token";
		access_token_url += "?code=" + code;
		access_token_url += "&grant_type=authorization_code";
		access_token_url += "&redirect_uri=" + oauthInfo.redirect_uri;

		response = restTemplate.exchange(access_token_url, HttpMethod.POST, request, String.class);


		// Get the Access Token From the recieved JSON response
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(response.getBody());
		System.out.println(node.toString());
		String access_token = node.path("access_token").asText();
		String refresh_token = node.path("refresh_token").asText();

		String url = oauthInfo.getTarget_uri() + "/api/users";

		// Use the access token for authentication

		HttpHeaders headers1 = new HttpHeaders();
		headers1.add("Authorization", "Bearer " + access_token);
		HttpEntity<String> entity = new HttpEntity<>(headers1);

		response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		System.out.println(response.getBody());
		node = mapper.readTree(response.getBody());
		String id = node.path("id").asText();
		String nickname = node.path("nickname").asText();

		ModelAndView model = new ModelAndView("test");
		model.addObject("id", id);
		model.addObject("nickname", nickname);
		model.addObject("access_token", access_token);

		return model;
	}

}