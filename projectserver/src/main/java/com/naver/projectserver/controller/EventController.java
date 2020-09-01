package com.naver.projectserver.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naver.projectserver.dto.EventDto;
import com.naver.projectserver.mapper.Event;
import com.naver.projectserver.service.EventService;

@RequestMapping("/api/event")
public class EventController {
	
	@Autowired
	EventService eventService;
	
	
	@GetMapping
	public ResponseEntity eventSearch(Principal principal) {
		String owner = principal.getName();
		List<Event> events = eventService.selectAll(owner);
		
		return ResponseEntity.ok(events);
	}
	
	@PostMapping
	public ResponseEntity eventRegister(Principal principal, @RequestBody @Valid EventDto eventDto) {
		
	}

}
