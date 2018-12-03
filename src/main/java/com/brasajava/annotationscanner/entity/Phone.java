package com.brasajava.annotationscanner.entity;

import com.brasajava.annotationscanner.entity.annotation.Masterdata;
import com.brasajava.annotationscanner.entity.annotation.MasterdataHost;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@MasterdataHost
public class Phone {
	@Masterdata(type = "phone_type")
	private Integer type;
	private String value;
}
