package com.rest.iotapi.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Device {

	// Device attributes
	private Integer id;
	private Date timestamp;
	private String status;

	private OperatingParams operatingParams;

	private Asset asset;

	private Parent parent;

}
