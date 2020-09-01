package com.naver.projectserver.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naver.projectserver.dto.EventDto;
import com.naver.projectserver.mapper.Event;
import com.naver.projectserver.service.EventService;

@RestController
@RequestMapping(value = "/api/events")
public class EventController {

	@Autowired
	EventService eventService;

	@Autowired
	ModelMapper modelMapper;

	@GetMapping
	public ResponseEntity eventSearch(Principal principal) {
		String owner = principal.getName();
		List<Event> events = eventService.selectAll(owner);
		if (events == null) {
			return ResponseEntity.badRequest().body("badRequest");
		}
		return ResponseEntity.ok(events);
	}

	@PostMapping
	public ResponseEntity eventRegister(Principal principal, @RequestBody @Valid EventDto eventDto) {
		Event event = modelMapper.map(eventDto, Event.class);

		event.update();
		event.setOwner(principal.getName());

		if (event.getEndEventDateTime() != null && event.getBeginEventDateTime() != null
				&& event.getEndEventDateTime().isBefore(event.getBeginEventDateTime())) {
			return ResponseEntity.badRequest().body("wrongInput");
		}

		if (eventService.insert(event)) {
			return ResponseEntity.ok("success");
		}

		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("fail");
	}

	@PutMapping
	public ResponseEntity eventUpdate(Principal principal, @RequestBody Event event) {
		event.setOwner(principal.getName());
		
		System.out.println(event);
		
		if(eventService.update(event)) {
			return ResponseEntity.ok("success");
		}
		
		return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("update fail");
		
		
		
	}

}
