package com.api.servicecustomer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.servicecustomer.entity.CustomerEntity;
import com.api.servicecustomer.repository.CustomerRepository;
import com.api.servicecustomer.response.CustomerResponse;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository custRepo;
	
	public CustomerResponse getCustomer(Integer id) {
		
		CustomerEntity customer = custRepo.findById(id).orElseThrow(()-> new RuntimeException("Customer ID not found!"));
		
		CustomerResponse custResponse = new CustomerResponse();
		custResponse.setId(customer.getId());
		custResponse.setName(customer.getName());
		custResponse.setEmail(customer.getEmail());
		custResponse.setPhone(customer.getPhone());
		
		return custResponse;
	}
}
