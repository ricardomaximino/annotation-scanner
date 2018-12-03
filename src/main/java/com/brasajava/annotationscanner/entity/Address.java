package com.brasajava.annotationscanner.entity;

import com.brasajava.annotationscanner.entity.annotation.Masterdata;
import com.brasajava.annotationscanner.entity.annotation.MasterdataHost;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@MasterdataHost
public class Address {
private String street;
@Masterdata(type = "province")
private Integer Province;
@Masterdata(type = "road_type")
private Integer roadType;
@Masterdata(type = "address_status")
private Integer type;
}
