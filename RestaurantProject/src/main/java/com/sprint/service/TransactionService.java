package com.sprint.service;

import com.sprint.dto.TransactionDTO;

import com.sprint.exceptions.CustomerNotFoundException;
import com.sprint.models.Customer;

public interface TransactionService {
	//public Transaction getTransactionByBookingId(Long bookingId)throws TransactionRecordNotFoundException;
// public Transaction getTransactionByCustomerId(Long Customer);
// public Transaction getAllTransactionByDateAndTime(Date date);
	
	public TransactionDTO createRecord(long id,TransactionDTO transaction);
	public Customer findCustomerById(long customerId) throws CustomerNotFoundException ;
 }
