package com.brasajava.annotationscanner.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brasajava.annotationscanner.entity.Person;

@RestController
@RequestMapping("/person")
public class PersonController {

	
	@PostMapping
	public Person create(@Valid @RequestBody Person person) {
		return person;
	}
}
