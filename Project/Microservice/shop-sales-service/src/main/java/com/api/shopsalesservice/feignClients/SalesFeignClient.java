package com.api.shopsalesservice.feignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.shopsalesservice.response.ProductResponse;

@FeignClient(name = "products-service", url = "${products-url}")
public interface SalesFeignClient {

	@GetMapping("/getallbyids")
	List<ProductResponse> getProductResponse(@RequestBody List<Integer> ids);
}
