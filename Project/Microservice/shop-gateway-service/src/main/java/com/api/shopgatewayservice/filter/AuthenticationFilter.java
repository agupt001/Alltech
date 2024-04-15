package com.api.shopgatewayservice.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.api.shopgatewayservice.util.JwtUtil;


@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<Config> {

	@Autowired
	private RouteValidator routeValidator;

	@Autowired
	private JwtUtil jwtUtil;
	
	public AuthenticationFilter() {
		super(Config.class);
	}
	
	@Override
	public GatewayFilter apply(Config config) {

		return ((exchange, chain)->{
			
			if(routeValidator.isSecured.test(exchange.getRequest())) {
				// Header contains token?
				if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException("Missing Authorization Header");
				}
				
				String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				if(authHeader!=null && authHeader.startsWith("Bearer ")){
					authHeader = authHeader.substring(7);
				}
				
				try {
					
					jwtUtil.validateToken(authHeader);
					
				} catch (Exception e) {
					System.out.println("Invalid Access");
					throw new RuntimeException("unauthorized access to application");
				}
			}
			
			return chain.filter(exchange);
		});
	}

}
