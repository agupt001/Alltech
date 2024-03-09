package com.rest.iotapi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

import com.rest.iotapi.entity.Device;
import com.rest.iotapi.request.DeviceRequest;
import com.rest.iotapi.response.DeviceResponse;
import com.rest.iotapi.service.DeviceService;

@SpringBootApplication
@EnableFeignClients
public class IotApiApplication {

	// Get the device request object
	private DeviceRequest request = new DeviceRequest();

	@Autowired
	private DeviceService service; // Get the service object

	public static void main(String[] args) {

		// Launch the application and receive the context
		ConfigurableApplicationContext context = SpringApplication.run(IotApiApplication.class, args);

		// Obtain a bean from the context and call the numDevices method
		IotApiApplication app = context.getBean(IotApiApplication.class);
		int deviceCount = app.numDevices(); // Get the device count
		System.out.println("Device Count = "+deviceCount); // Print the device count
		System.exit(0); // Exit the application
	}

	// Get user input
	public void userInput() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the status query.");
		String statusQuery = sc.next();

		System.out.println("Enter the threshold.");
		int threshold = sc.nextInt();

		System.out.println("Enter the date. (Format: MM-YYYY)");
		String date = sc.next();

		// Set the request attributes
		request.setStatusQuery(statusQuery);
		request.setThreshold(threshold);
		request.setDateStr(date);
		sc.close();
	}

	// Generate Data from URL and implement logic
	public int numDevices() {
		
		userInput(); // Get user input
		int deviceCount = 0; // Maintain device count
		
		// Generate a date formatter
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-yyyy");
		
		// Loop 3 times for three pages
		for (int i = 1; i < 4; i++) {
			
			// Get the response from the URL
			// Set the parameters status query and page number
			DeviceResponse dr = service.searchDevices(request.getStatusQuery(), i);

			// Loop through each device data
			for (Device device : dr.getData()) {

				// Get the root threshold of each device
				int rootThreshold = device.getOperatingParams().getRootThreshold();
				
				// Get the time stamp of each device
				Date deviceTimestamp = device.getTimestamp();
				
				// Format the time stamp in the date format
				String timestampFormatted = dateFormatter.format(deviceTimestamp);
				
				// Check the required condition, floor is automatically applied to the threshold when type casting
				if (rootThreshold >= request.getThreshold() && timestampFormatted.equals(request.getDateStr())) {
					deviceCount++; // increment the device counter
				}
					

			}
		}

		return deviceCount;
	}

}
