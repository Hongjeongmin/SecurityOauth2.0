package com.naver.projectserver.resource;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.EntityModel;

import com.naver.projectserver.controller.EventController;
import com.naver.projectserver.mapper.Event;

public class EventResource extends EntityModel<Event>{
	
	Event event;
	
	public EventResource(Event event) {
		this.event = event;
		add(linkTo(EventController.class).slash(event.getId()).withSelfRel());
	}
	
	


}
