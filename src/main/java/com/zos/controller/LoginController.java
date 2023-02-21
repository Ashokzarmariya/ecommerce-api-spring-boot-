package com.zos.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zos.exception.UserException;
import com.zos.modal.Customer;
import com.zos.repository.CustomerRepository;



@RestController
public class LoginController {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	
	@GetMapping("/signin")
	public ResponseEntity<Customer> loginHandler(Authentication auth) throws UserException{
		
		Optional<Customer> opt=customerRepo.findByEmail(auth.getName());
		
		if(opt.isPresent()) {
			return new ResponseEntity<Customer>(opt.get(),HttpStatus.OK);
		}
		throw new UserException("Customer not Exist - "+auth.getName());
		
		
		
	}

}
