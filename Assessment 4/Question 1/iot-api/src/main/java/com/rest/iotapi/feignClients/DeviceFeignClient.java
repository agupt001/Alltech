package com.rest.iotapi.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rest.iotapi.response.DeviceResponse;

// Address URL in the properties file
@FeignClient(name = "hackerrank", url="${address-url}")
public interface DeviceFeignClient {

	// Set up a feign client to fetch data from URL
	// Status and page number will be set dynamically from the main class
	@GetMapping
	DeviceResponse searchUrlForDevices(@RequestParam("status") String statusQuery,
							@RequestParam("page") int pageNumber);
}
