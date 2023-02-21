package com.zos.service;

import com.zos.exception.UserException;
import com.zos.modal.Address;
import com.zos.modal.Customer;

public interface CustomerServices {
	
//	public Customer retisterCustomer(Customer user);
	public Customer addNewAddress(Integer userId,Address data) throws UserException;
	public Customer registerCustomer(Customer user);
	

}
