package com.naver.projectserver.resource;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.validation.Errors;


public class ErrorsResource extends EntityModel<Errors> {
	Errors errors;
	
	
	
    public ErrorsResource(Errors errors) {
		this.errors = errors;
//		add(linkTo(methodOn(IndexController.class).index()).withRel("index"));
	}



	public ErrorsResource( Link... links) {
    }
}
