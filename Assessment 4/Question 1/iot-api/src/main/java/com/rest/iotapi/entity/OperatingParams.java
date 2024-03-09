package com.rest.iotapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperatingParams {

	// OperatingParams attributes
	private int rotorSpeed;

	private int slack;

	private int rootThreshold;

}
