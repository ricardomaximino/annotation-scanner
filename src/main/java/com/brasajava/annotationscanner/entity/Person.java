package com.brasajava.annotationscanner.entity;

import java.util.List;

import javax.validation.Valid;

import com.brasajava.annotationscanner.entity.annotation.Masterdata;
import com.brasajava.annotationscanner.entity.annotation.MasterdataHost;
import com.brasajava.annotationscanner.entity.annotation.MasterdataHost.Level;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@MasterdataHost(level = Level.ROOT)
public class Person {

	private String name;
	private String lastname;
	private String email;
	@Masterdata(type = "person_age")
	private Integer age;
	@Valid
	private Address address;
	@Masterdata(type = "person_status")
	private Integer status;
	@Masterdata(type = "person_type")
	private Integer type;
	private String countryCode;
	private List<Phone> phones;

}
