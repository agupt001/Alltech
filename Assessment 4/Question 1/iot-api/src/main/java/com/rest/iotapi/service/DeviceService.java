package com.rest.iotapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.iotapi.feignClients.DeviceFeignClient;
import com.rest.iotapi.response.DeviceResponse;

// Set up a service to call Feign Client
@Service
public class DeviceService {

	@Autowired
	private DeviceFeignClient deviceFeignClient; // Auto wire feign client object
	
	// Method to retrieve server URL object
	public DeviceResponse searchDevices(String status, int page) {
		return deviceFeignClient.searchUrlForDevices(status, page);
	}
}
