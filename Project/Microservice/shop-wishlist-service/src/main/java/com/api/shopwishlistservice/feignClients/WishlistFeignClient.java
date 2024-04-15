package com.api.shopwishlistservice.feignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.shopwishlistservice.response.ProductResponse;

@FeignClient(name = "products-service", url = "${products-url}")
public interface WishlistFeignClient {

	@PostMapping("/getallbyids")
	List<ProductResponse> getProductResponse(@RequestBody List<Integer> ids);
}
