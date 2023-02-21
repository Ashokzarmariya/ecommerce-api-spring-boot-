package com.zos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zos.exception.UserException;
import com.zos.modal.Address;
import com.zos.modal.Customer;
import com.zos.repository.CustomerRepository;

@Service
public class UserServiceImplementation implements CustomerServices {
	
	@Autowired
	private CustomerRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Customer registerCustomer(Customer user) {
		
		String hashPassword= passwordEncoder.encode(user.getPassword());
		
		Customer data=new Customer();
		data.setFirstName(user.getFirstName());
		data.setLastName(user.getLastName());
		data.setEmail(user.getEmail());
		data.setPassword(hashPassword);
		
		
		Customer createdUser=userRepo.save(data);
		
		return createdUser;
	}

	@Override
	public Customer addNewAddress(Integer userId, Address data) throws UserException {
		
		Optional<Customer> opt =userRepo.findById(userId);
		
		
		 
		
			
		
			
		if(opt.isPresent()) {
			System.out.println("After if ---- " + data.getCity()+data.getPincode()+data.getState());
			
			Customer user = opt.get();
			
			user.getAddresses().add(new Address(data.getState(),data.getCity(),data.getPincode()));
		
			System.out.println("user : "+user.getAddresses());
			System.out.println(user.toString());
		
			Customer savedUser = userRepo.save(user);
		
			System.out.println("saved user -- "+ savedUser);
			
			return savedUser;
		}
		
		throw new UserException("user not exist");
		
		
	}

}
