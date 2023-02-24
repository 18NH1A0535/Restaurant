package com.sprint.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.exceptions.CustomerNotFoundException;
import com.sprint.exceptions.InvalidCredentialsException;
import com.sprint.models.Admin;
import com.sprint.models.Customer;
import com.sprint.repository.AdminRepository;
import com.sprint.repository.BookingRepository;
import com.sprint.repository.CustomerRepository;
import com.sprint.repository.TransactionRepository;

@Service
public class AdminImpl implements AdminService{

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private CustomerRepository customerRepository;
	private BookingRepository bookingRepository;
	@Autowired
	private TransactionRepository transactionRepository;
	
	public Admin registerAdmin(Admin admin) {
		return adminRepository.save(admin);
	}
	
	public String loginAdmin(String email,String password) throws InvalidCredentialsException{
		if(adminRepository.validateAdmin(email,password)==null) {
			throw new InvalidCredentialsException("Credentials given are Invalid!");
			}
		return "Login Successful";
		}
	
	@Override
	public double calculateMoneySpent(long customerId) throws CustomerNotFoundException {
		Customer existingCustomer =transactionService.findCustomerById(customerId);
		return transactionRepository.findTotalCostSpent(customerId);
	}	

	
	@Override
	public double discountsForCustomers(Long customerId) throws CustomerNotFoundException {
		Optional<Customer> customer = customerRepository.findById(customerId);
		double discount = 0.0;
	    if (!customer.isPresent()) {
	    	throw new CustomerNotFoundException("Customer with given Id is not Found");
	    }
	   // Customer c = customer.get();
	    
//	    c.setBookingsCount(c.getBookingsCount() + 1);
//	    customerRepository.save(c);
	    
	    else if (bookingRepository.count() >= 5) {
	      discount = 0.10;
	    } else if (bookingRepository.count() >= 10) {
	    	discount = 0.15;
    }

	  return discount;
	}

}

