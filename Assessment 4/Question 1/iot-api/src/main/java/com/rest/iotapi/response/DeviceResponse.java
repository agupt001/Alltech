package com.rest.iotapi.response;

import java.util.ArrayList;

import com.rest.iotapi.entity.Device;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceResponse {

	// Response from server URL
	private int page;
	private int per_page;
	private int total;
	private int total_pages;
	private ArrayList<Device> data;
}
