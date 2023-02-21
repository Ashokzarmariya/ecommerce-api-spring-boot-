package com.zos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zos.exception.UserException;
import com.zos.modal.Address;
import com.zos.modal.Customer;
import com.zos.service.CustomerServices;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerServices userServices;
	
	@PostMapping("/signup")
	public ResponseEntity<Customer> createUserHandler(@RequestBody Customer user){


		System.out.println("customer - "+user.getFirstName());
		
		Customer registeredUser = userServices.registerCustomer(user);
		
		System.out.println("registeredUser -- "+registeredUser.getEmail());
		
		return new ResponseEntity<Customer>(registeredUser,HttpStatus.CREATED);
	}
	
	@PutMapping("user/new_address/{userId}")
	public ResponseEntity<Customer> addNewAddressHandler(@PathVariable Integer userId,@RequestBody Address address) throws UserException{
		
		Customer updatedUser= userServices.addNewAddress(userId, address);
		return new ResponseEntity<Customer>(updatedUser,HttpStatus.OK);
		
	}
	

}
