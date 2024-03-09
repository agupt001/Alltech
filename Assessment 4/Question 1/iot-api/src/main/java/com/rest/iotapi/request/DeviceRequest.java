package com.rest.iotapi.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceRequest {

	// Request from user attributes
	private String statusQuery;
	private int threshold;
	private String dateStr;
	
}
