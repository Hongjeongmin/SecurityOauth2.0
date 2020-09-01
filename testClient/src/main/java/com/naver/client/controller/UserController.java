package com.naver.client.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naver.client.dto.User;
import com.naver.client.dto.UserDto;
import com.naver.client.dto.UserResource;
import com.naver.client.userservice.UserService;

@RequestMapping(value = "/api/users",produces = MediaTypes.HAL_JSON_UTF8_VALUE)
@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	@GetMapping
	public ResponseEntity searchUser(){
		URI createUri = linkTo(methodOn(UserController.class).searchUser()).toUri();
		
		List<User> users = userService.selectAll();
		
		UserResource userResource = new UserResource(users);
		userResource.add(linkTo(UserController.class).withSelfRel());
		userResource.add(linkTo(UserController.class).withRel("user-update"));
		return ResponseEntity.created(createUri).body(userResource);
	}
	
	@PostMapping 
	public ResponseEntity createUser(@RequestBody @Valid UserDto userDto,Errors errors){
		if(errors.hasErrors()) {
			return ResponseEntity.badRequest().body(errors);
		}
		
		User user = modelMapper.map(userDto, User.class);
		URI createUri = linkTo(UserController.class).toUri();
		return ResponseEntity.created(createUri).body(user);
	}
	


}
