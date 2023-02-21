package com.zos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import com.zos.modal.Customer;
import com.zos.repository.CustomerRepository;

@Service
public class UserUserDetailService implements UserDetailsService {
	
	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<Customer> opt=customerRepo.findByEmail(username);
		
		if(opt.isPresent()) {
			
			Customer customer=opt.get();
			
			List<GrantedAuthority> authorities= new ArrayList<>();
			//authorities.add(new SimpleGrantedAuthority(customer.getRole()));
			
			return new User(customer.getEmail(),customer.getPassword(),authorities);
		}
		throw new BadCredentialsException("Bad Credential");
		
	}

}
